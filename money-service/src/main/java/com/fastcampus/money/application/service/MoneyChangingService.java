package com.fastcampus.money.application.service;

import com.fastcampus.money.adapter.axon.command.CreatedMemberMoneyCommand;
import com.fastcampus.money.adapter.axon.event.IncreaseMemberMoneyEvent;
import com.fastcampus.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.fastcampus.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import com.fastcampus.money.application.port.in.*;
import com.fastcampus.money.application.port.out.external.GetMemberMoneyPort;
import com.fastcampus.money.application.port.out.external.GetMembershipPort;
import com.fastcampus.money.application.port.out.external.MembershipStatus;
import com.fastcampus.money.application.port.out.kafka.SendRechargingMoneyTaskPort;
import com.fastcampus.money.application.port.out.persistence.CreateMemberMoneyPort;
import com.fastcampus.money.application.port.out.persistence.IncreaseMoneyPort;
import com.fastcampus.money.application.port.out.persistence.MoneyChangingPort;
import com.fastcampus.money.domain.MemberMoney;
import com.fastcampus.money.domain.MoneyChangingRequest;
import com.fastcampuspay.common.CountDownLatchManager;
import com.fastcampuspay.common.annotation.UseCase;
import com.fastcampuspay.common.task.RechargingMoneyTask;
import com.fastcampuspay.common.task.SubTask;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.ArrayList;
import java.util.UUID;

import static com.fastcampus.money.domain.MoneyChangingRequest.ChangingStatus.FAILED;
import static com.fastcampus.money.domain.MoneyChangingRequest.ChangingStatus.SUCCEEDED;
import static com.fastcampus.money.domain.MoneyChangingRequest.ChangingType.INCREASING;

@UseCase
@RequiredArgsConstructor
@AllArgsConstructor
@Transactional
public class MoneyChangingService implements IncreaseMoneyReqUseCase, CreateMemberMoneyUseCase {

    private static MoneyChangingPort registerMoneyChangingPort;
    private static GetMembershipPort getMembershipPort;
    private static SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;
    private static CreateMemberMoneyPort createMemberMoneyPort;
    private static GetMemberMoneyPort getMemberMoneyPort;
    private static IncreaseMoneyPort increaseMoneyPort;
    private static IncreaseMemberMoneyEvent increaseMemberMoneyEvent;
    private static CountDownLatchManager countDownLatchManager;

    private final CommandGateway commandGateway;
    private final MoneyChangingRequestMapper mapper;


    @Override
    public MoneyChangingRequest increaseMoney(RequestIncreaseMoneyCommand command) {

        // 머니 충전(증액)
        // 1. 고객 정보 정상 확인 (멤버)
        getMembershipPort.getMembership(command.getTargetMembershipId());

        // 2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지 확인 (뱅킹)
        // 3. 법인 계좌 상태도 정상인지 확인 (뱅킹)
        // 4. 증액을 위한 "기록", 요청상태로 request 생성
        // 5. 펌뱅킹을 수행하고 (고객 연동 계좌 -> 패켐페이 법인 계좌 이체) (뱅킹)
        // 5-1.

        // 6-1. 결과가 정상적이라면, 성공으로 request 상태값 변동 후 리턴
        // 성공 시 멤버의 money 값 증액 필요.
        registerMoneyChangingPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId()),
                new MemberMoney.Balance(command.getAmount()));

        // 6-2. 결과가 실패라면, 실패로 request 상태값 변경 후 리턴

        return registerMoneyChangingPort.saveMoneyChangingRequest(
                new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                new MoneyChangingRequest.ChangingMoneyType(INCREASING),
                new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                new MoneyChangingRequest.ChangingMoneyStatus(SUCCEEDED),
                UUID.randomUUID());
    }

    @Override
    public MoneyChangingRequest increaseMoneyAsync(RequestIncreaseMoneyCommand command) {
        // 1. subtask, task
        // membership subtask : 각 서비스에 membership id 로 validation 을 하기 위한 Task
        SubTask validMemberTask = SubTask.builder()
                .subTaskName("validMemberTask : " + "멤버십 유효성 검사")
                .membershipId(String.valueOf(command.getTargetMembershipId()))
                .taskType("membership")
                .status("ready")
                .build();

        // banking sub task : banking account validation
        SubTask validAccountTask = SubTask.builder()
                .subTaskName("validMemberTask : " + "뱅킹 계좌 유효성 검사")
                .membershipId(String.valueOf(command.getTargetMembershipId()))
                .taskType("banking")
                .status("ready")
                .build();
        // amount money Firmbanking -> 무조건 ok 받았다고 가정. (subtask)

        ArrayList<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(validMemberTask);
        subTaskList.add(validAccountTask);

        RechargingMoneyTask moneyTask = RechargingMoneyTask.builder()
                .taskId(UUID.randomUUID().toString())
                .taskName("Increase Money Task / 머니 충전 Task")
                .subTaskList(subTaskList)
                .moneyAmount((int) command.getAmount())
                .membershipId(String.valueOf(command.getTargetMembershipId()))
                .toBankName("fastcampus")
                .build();

        // 2. kafka cluster produce
        // task produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(moneyTask);
        countDownLatchManager.addCountDownLatch(moneyTask.getTaskId());

        // 3. wait
        try {
            countDownLatchManager.getCountDownLatch(moneyTask.getTaskId()).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 3-1. task-consumer
        // 등록된 sub-task, status 모두 ok => task 결과를 produce

        // 4. 다른 큐에서 task result consumer
        // 받은 응답을 다시 countDownLatchManager 통해 결과 데이터를 받아야 함.
        String result = countDownLatchManager.getDataForKey(moneyTask.getTaskId());
        if (result.equals("success")) {
            // 4-1. success 인 경우
            registerMoneyChangingPort.increaseMoney(
                    new MemberMoney.MembershipId(command.getTargetMembershipId()),
                    new MemberMoney.Balance(command.getAmount()));

            return registerMoneyChangingPort.saveMoneyChangingRequest(
                    new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                    new MoneyChangingRequest.ChangingMoneyType(INCREASING),
                    new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                    new MoneyChangingRequest.ChangingMoneyStatus(SUCCEEDED),
                    UUID.fromString(moneyTask.getTaskId()));
        } else {
            // 4-2. failed 인 경우
            return registerMoneyChangingPort.saveMoneyChangingRequest(
                    new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                    new MoneyChangingRequest.ChangingMoneyType(INCREASING),
                    new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                    new MoneyChangingRequest.ChangingMoneyStatus(FAILED),
                    UUID.fromString(moneyTask.getTaskId()));
        }

        // 5. consume ok => 추가 business 로직이 필요한 경우

    }

    @Override
    public void increaseMoneyRequestByEvent(IncreaseMoneyReqCommand command) {
        MembershipStatus membership = getMembershipPort.getMembership(command.getMembershipId());

        commandGateway.send(command)
                .whenComplete(
                        (result, throwable) -> {
                            if (throwable != null) {
                                System.out.println("throwable = " + throwable);
                                throw new RuntimeException(throwable);
                            } else {
                                System.out.println("result = " + result);

                                //increase money
                                MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                                        new MemberMoney.MembershipId(membership.getMembershipId()),
                                        command.getAmount());

                                if (memberMoneyJpaEntity != null) {
//                                    mapper.mapToMemberMoneyEntity(
//                                            increaseMoneyPort.createMoneyChangingRequest(
//                                                    new MoneyChangingRequest.TargetMembershipId(command.getMembershipId()),
//                                                    new MoneyChangingRequest.ChangingMoneyType(INCREASING),
//                                                    new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
//                                                    new MoneyChangingRequest.ChangingMoneyStatus(SUCCEEDED),
//                                                    new MoneyChangingRequest.Uuid()));
                                }
                            }
                        }
                );

    }

    @Override
    public void CreateMemberMoney(CreateMemberMoneyCommand command) {
        CreatedMemberMoneyCommand axonCommand = new CreatedMemberMoneyCommand(command.getTargetMembershipId());
        commandGateway.send(axonCommand)
                .whenComplete(
                        (result, throwable) -> {
                            if (throwable != null) {
                                System.out.println("MoneyChangingService.CreateMemberMoney");
                                throw new RuntimeException(throwable);
                            } else {
                                System.out.println("result = " + result);
                                createMemberMoneyPort.createMemberMoney(
                                        new MemberMoney.MembershipId(command.getTargetMembershipId()),
                                        new MemberMoney.MoneyAggregateIdentifier(result.toString())
                                );
                            }
                        }); // send를 하는 순간 axonserver에 event queue 에 보내게 된다.

    }
}
