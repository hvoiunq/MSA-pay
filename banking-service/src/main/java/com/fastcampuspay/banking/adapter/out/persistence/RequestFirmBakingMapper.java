package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.adapter.out.persistence.entity.RequestFirmBankingJpaEntity;
import com.fastcampuspay.banking.domain.FirmBankingRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestFirmBakingMapper {

    public FirmBankingRequest mapToDomainEntity(RequestFirmBankingJpaEntity requestFirmBankingJpaEntity, UUID uuid) {
        return FirmBankingRequest.generateFirmBankingRequest(
                new FirmBankingRequest.FirmBankRequestId(requestFirmBankingJpaEntity.getRequestFirmBankingId()),
                new FirmBankingRequest.FromBankName(requestFirmBankingJpaEntity.getFromBankAccountNumber()),
                new FirmBankingRequest.FromBankAccountNumber(requestFirmBankingJpaEntity.getFromBankAccountNumber()),
                new FirmBankingRequest.ToBankName(requestFirmBankingJpaEntity.getToBankName()),
                new FirmBankingRequest.ToBankAccountNumber(requestFirmBankingJpaEntity.getFromBankAccountNumber()),
                new FirmBankingRequest.MoneyAmount(requestFirmBankingJpaEntity.getMoneyAccount()),
                new FirmBankingRequest.FirmBankingStatus(requestFirmBankingJpaEntity.getStatus()),
                uuid);
    }

}
