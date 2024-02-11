package com.fastcampuspay.banking.application.port.out.external;

import com.fastcampuspay.banking.adapter.out.external.request.ExternalFirmBankingRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.FirmBankingResult;

public interface RequestExternalFirmBankingPort {
    FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest externalFirmBankingRequest);
}
