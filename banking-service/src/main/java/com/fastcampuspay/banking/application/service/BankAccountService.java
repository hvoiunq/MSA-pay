package com.fastcampuspay.banking.application.service;

import com.fastcampuspay.banking.adapter.out.external.request.ExternalBankAccountRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.ExternalBankAccount;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountUseCase;
import com.fastcampuspay.banking.application.port.out.external.GetMembershipPort;
import com.fastcampuspay.banking.application.port.out.external.MembershipStatus;
import com.fastcampuspay.banking.application.port.out.persistence.RegisterBankAccountPort;
import com.fastcampuspay.banking.application.port.out.external.RequestBankAccountInfoPort;
import com.fastcampuspay.banking.domain.BankAccount;
import com.fastcampuspay.common.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class BankAccountService implements RegisterBankAccountUseCase {

    private final GetMembershipPort getMembershipPort;
    private final RegisterBankAccountPort registerBankAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;

    /***
     * 은행 계좌 등록 서비스
     * @param command {@link RegisterBankAccountCommand}
     * @return {@link BankAccount}
     * @see <a href = "https://github.com/hvoiunq/MSA-pay"> github </a>
     */
    @Override
    public BankAccount registerBankAccount(RegisterBankAccountCommand command) {


        // 멤버 확인
        MembershipStatus membership = getMembershipPort.getMembership(String.valueOf(command.getMembershipId()));
        if (membership == null) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        // 1. 외부 실제 은행에 등록 가능한 은행인지 확인
        // biz logic -> exception
        // Port -> adapter -> External System
        ExternalBankAccount bankAccountInfo = requestBankAccountInfoPort.getBankAccountInfo(new ExternalBankAccountRequest(command.getBankName(), command.getBankAccountNumber(), command.isValid()));
        boolean accountInfoValid = bankAccountInfo.isValid();


        // 2. 등록가능한 계좌라면, 등록한다. 성공하면, 등록에 성공한 등록 정보를 리턴
        // 2-1. 등록가능하지 않은 계좌라면 에러 리턴

        if (accountInfoValid) {
            return registerBankAccountPort.createBankAccount(
                    new BankAccount.MembershipId(command.getMembershipId()),
                    new BankAccount.BankName(command.getBankName()),
                    new BankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new BankAccount.LinkedStatusIsValid(command.isValid()));
        }

        return null;
    }
}
