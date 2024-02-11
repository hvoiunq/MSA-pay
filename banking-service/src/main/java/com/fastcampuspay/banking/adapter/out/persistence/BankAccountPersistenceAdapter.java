package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.adapter.out.persistence.entity.BankAccountJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.entity.RequestFirmBankingJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.entity.SpringDataBankAccountRepository;
import com.fastcampuspay.banking.adapter.out.persistence.entity.SpringDataFirmBankingRequestRepository;
import com.fastcampuspay.banking.application.port.out.persistence.RegisterBankAccountPort;
import com.fastcampuspay.banking.application.port.out.persistence.RequestFirmBankingPort;
import com.fastcampuspay.banking.domain.BankAccount;
import com.fastcampuspay.banking.domain.FirmBankingRequest;
import com.fastcampuspay.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountPersistenceAdapter implements RegisterBankAccountPort, RequestFirmBankingPort {

    private final SpringDataBankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;
    private final SpringDataFirmBankingRequestRepository firmBankingRequestRepository;
    private final RequestFirmBakingMapper requestFirmBakingMapper;

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

    @Override
    public FirmBankingRequest createFirmBankingRequest(FirmBankingRequest.FromBankName fromBankName,
                                                       FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
                                                       FirmBankingRequest.ToBankName toBankName,
                                                       FirmBankingRequest.ToBankAccountNumber toBankAccountNumber,
                                                       FirmBankingRequest.MoneyAmount moneyAmount,
                                                       FirmBankingRequest.FirmBankingStatus firmBankingStatus,
                                                       UUID uuid) {
        RequestFirmBankingJpaEntity saved = firmBankingRequestRepository.save(
                new RequestFirmBankingJpaEntity(
                        fromBankName.getValue(),
                        fromBankAccountNumber.getValue(),
                        toBankName.getValue(),
                        toBankAccountNumber.getValue(),
                        moneyAmount.getValue(),
                        firmBankingStatus.getValue(),
                        uuid
                )
        );

        return requestFirmBakingMapper.mapToDomainEntity(saved, uuid);
    }
}
