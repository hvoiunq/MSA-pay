package com.fastcampus.money.application.service;

import com.fastcampus.money.application.port.in.IncreaseMoneyReqUseCase;
import com.fastcampus.money.application.port.in.RequestIncreaseMoneyCommand;
import com.fastcampus.money.application.port.out.persistence.MoneyChangingPort;
import com.fastcampus.money.domain.MemberMoney;
import com.fastcampus.money.domain.MoneyChangingRequest;
import com.fastcampuspay.common.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static com.fastcampus.money.domain.MoneyChangingRequest.ChangingStatus.SUCCEEDED;
import static com.fastcampus.money.domain.MoneyChangingRequest.ChangingType.INCREASING;

@UseCase
@RequiredArgsConstructor
@Transactional
public class MoneyChangingService implements IncreaseMoneyReqUseCase {

    private static MoneyChangingPort registerMoneyChangingPort;

    @Override
    public MoneyChangingRequest increaseMoney(RequestIncreaseMoneyCommand command) {

        // 머니 충전(증액)
        // 1. 고객 정보 정상 확인 (멤버)
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
}
