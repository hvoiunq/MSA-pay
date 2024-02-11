package com.fastcampuspay.banking.application.port.out.persistence;

import com.fastcampuspay.banking.adapter.out.persistence.entity.RequestFirmBankingJpaEntity;
import com.fastcampuspay.banking.domain.FirmBankingRequest;

import java.util.UUID;

public interface RequestFirmBankingPort {
    FirmBankingRequest createFirmBankingRequest(
            FirmBankingRequest.FromBankName fromBankName,
            FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmBankingRequest.ToBankName toBankName,
            FirmBankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmBankingRequest.MoneyAmount moneyAmount,
            FirmBankingRequest.FirmBankingStatus firmBankingStatus,
            UUID uuid
    );

    FirmBankingRequest modifyFirmBankingRequest(
            FirmBankingRequest.FirmBankingStatus firmBankingStatus,
            UUID uuid
    );

}
