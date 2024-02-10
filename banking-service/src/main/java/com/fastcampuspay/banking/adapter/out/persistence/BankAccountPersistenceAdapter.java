package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.adapter.out.persistence.entity.BankAccountJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.entity.SpringDataBankAccountRepository;
import com.fastcampuspay.banking.application.port.out.RegisterBankAccountPort;
import com.fastcampuspay.banking.domain.BankAccount;
import com.fastcampuspay.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountPersistenceAdapter implements RegisterBankAccountPort {

    private final SpringDataBankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;


    @Override
    public BankAccount createBankAccount(BankAccount.MembershipId membershipId,
                                         BankAccount.BankName bankName,
                                         BankAccount.BankAccountNumber bankAccountNumber,
                                         BankAccount.LinkedStatusIsValid linkedStatusIsValid) {

        BankAccountJpaEntity bankAccountJpaEntity = bankAccountRepository.save(
                new BankAccountJpaEntity(
                        membershipId.getValue(),
                        bankName.getValue(),
                        bankAccountNumber.getValue(),
                        linkedStatusIsValid.isValue()
                )
        );

        return bankAccountMapper.mapToDomainEntity(bankAccountJpaEntity);
    }
}
