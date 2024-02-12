package com.fastcampuspay.banking.application.port.out.external;

import com.fastcampuspay.banking.adapter.out.external.request.ExternalFirmBankingRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.FirmBankingResult;
import com.fastcampuspay.banking.domain.FirmBankingRequest;

public interface RequestExternalFirmBankingPort {
    FirmBankingResult requestExternalFirmBanking(FirmBankingRequest.FromBankName fromBankName,
                                                 FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
                                                 FirmBankingRequest.ToBankName toBankName,
                                                 FirmBankingRequest.ToBankAccountNumber toBankAccountNumber);
}
