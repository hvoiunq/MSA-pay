package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.adapter.out.persistence.entity.BankAccountJpaEntity;
import com.fastcampuspay.banking.domain.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {

    public BankAccount mapToDomainEntity(BankAccountJpaEntity bankAccountJpaEntity) {
        return BankAccount.generateBankAccount(
                new BankAccount.BankAccountId(bankAccountJpaEntity.getBankAccountId()),
                new BankAccount.MembershipId(bankAccountJpaEntity.getMembershipId()),
                new BankAccount.BankName(bankAccountJpaEntity.getBankName()),
                new BankAccount.BankAccountNumber(bankAccountJpaEntity.getBankAccountNumber()),
                new BankAccount.LinkedStatusIsValid(bankAccountJpaEntity.isLinkedStatusIsValid())
        );
    }

}
