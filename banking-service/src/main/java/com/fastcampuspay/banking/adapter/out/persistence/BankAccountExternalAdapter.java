package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.adapter.out.persistence.entity.BankAccountJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.entity.SpringDataBankAccountRepository;
import com.fastcampuspay.banking.application.port.out.RegisterBankAccountPort;
import com.fastcampuspay.banking.domain.BankAccount;
import com.fastcampuspay.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountExternalAdapter implements RegisterBankAccountPort {

    private final SpringDataBankAccountRepository bankAccountRepository;
    private final BankAccountMaper bankAccountMaper;


    @Override
    public BankAccount createBankAccount(BankAccount.BankAccountId bankAccountId, BankAccount.MembershipId membershipId, BankAccount.BankName bankName, BankAccount.BankAccountNumber bankAccountNumber, BankAccount.LinkedStatusIsValid linkedStatusIsValid) {
        BankAccountJpaEntity bankAccountJpaEntity = new BankAccountJpaEntity();
        bankAccountJpaEntity.setBankAccountId(bankAccountId.getValue());
        bankAccountJpaEntity.setMembershipId(membershipId.getValue());
        bankAccountJpaEntity.setBankName(bankName.getValue());
        bankAccountJpaEntity.setBankAccountNumber(bankAccountNumber.getValue());
        bankAccountJpaEntity.setLinkedStatusIsValid(linkedStatusIsValid.isValue());
        bankAccountRepository.save(bankAccountJpaEntity);

        return bankAccountMaper.mapToDomainEntity(bankAccountJpaEntity);
    }
}
