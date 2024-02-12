package com.fastcampuspay.banking.adapter.out.external;

import com.fastcampuspay.banking.adapter.out.external.request.ExternalFirmBankingRequest;
import com.fastcampuspay.banking.domain.FirmBankingRequest;

public class ExternalFirmBankingMapper {
    public ExternalFirmBankingRequest mapToDomainRequest(FirmBankingRequest.FromBankName fromBankName,
                                                         FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
                                                         FirmBankingRequest.ToBankName toBankName,
                                                         FirmBankingRequest.ToBankAccountNumber toBankAccountNumber) {
        return ExternalFirmBankingRequest.of(
                fromBankName.getValue(),
                fromBankAccountNumber.getValue(),
                toBankName.getValue(),
                toBankAccountNumber.getValue()
        );
    }
}
