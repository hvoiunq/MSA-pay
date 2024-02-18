package com.fastcampus.remittanceservice.application.service;

import com.fastcampus.remittanceservice.application.port.in.RemittanceReqUseCase;
import com.fastcampus.remittanceservice.application.port.in.RequestRemittanceCommand;
import com.fastcampus.remittanceservice.application.port.out.membership.MembershipPort;
import com.fastcampus.remittanceservice.application.port.out.money.MoneyPort;
import com.fastcampus.remittanceservice.domain.Remittance;
import com.fastcampuspay.common.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@AllArgsConstructor
@Transactional
public class RequestRemittanceService implements RemittanceReqUseCase {

    private MoneyPort moneyPort;
    private MembershipPort membershipPort;
    @Override
    public Remittance requestRemittance(RequestRemittanceCommand requestRemittanceCommand) {

        // 0. 송금 요청 상태룰 사작상태로 기록 (remittance)
        // 1. from 멤버십 상태 확인 (membership)

        // 2. 잔액 존재하는지 확인 (money)
        // 2-1. 잔액이 충분하지 않다면, 충전 요청 (money)

        // 3. 송금 타입(내부고객/타행)
        // 3-1. 내부 고객일 경우
        // from 고객 머니 감액, to 고객 머니 증액 (money)

        // 3-2. 외부 은행 계좌
        // 외부 은행 계좌가 적절한지 확인 (banking)
        // 법인계좌 -> 외부 은행 계좌 펌뱅킹 요청 (firm banking)

        // 4. 송금 요청상태를 성공으로 기록 (remittance - persistance)
        // 4-1. 송금 완료된 기록 (remittance - persistance)

        return null;
    }
}
