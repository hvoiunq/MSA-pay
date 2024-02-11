package com.fastcampuspay.banking.adapter.out.external.bank;

import com.fastcampuspay.banking.adapter.out.external.request.ExternalBankAccountRequest;
import com.fastcampuspay.banking.adapter.out.external.request.ExternalFirmBankingRequest;
import com.fastcampuspay.banking.application.port.out.external.RequestBankAccountInfoPort;
import com.fastcampuspay.banking.application.port.out.external.RequestExternalFirmBankingPort;
import com.fastcampuspay.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;


@ExternalSystemAdapter
@RequiredArgsConstructor
public class ExternalBankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmBankingPort {

    @Override
    public ExternalBankAccount getBankAccountInfo(ExternalBankAccountRequest request) {
        // 실제 API 구현 불가로 임시 return
        return new ExternalBankAccount(request.getName(), request.getBankAccountNumber(), true);
    }

    @Override
    public FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest externalFirmBankingRequest) {
        // 실제 외부 은행 http 통신 후 펌뱅킹 요청

        // 결과 외부 은행의 실제 결과를 firmbanking result로 파싱
        return new FirmBankingResult(0);
    }
}
