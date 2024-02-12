package com.fastcampuspay.banking.adapter.out.external;

import com.fastcampuspay.banking.adapter.out.external.bank.ExternalBankAccount;
import com.fastcampuspay.banking.adapter.out.external.bank.FirmBankingResult;
import com.fastcampuspay.banking.adapter.out.external.request.ExternalBankAccountRequest;
import com.fastcampuspay.banking.adapter.out.external.request.ExternalFirmBankingRequest;
import com.fastcampuspay.banking.application.port.out.external.RequestBankAccountInfoPort;
import com.fastcampuspay.banking.application.port.out.external.RequestExternalFirmBankingPort;
import com.fastcampuspay.banking.domain.FirmBankingRequest;
import com.fastcampuspay.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;


@ExternalSystemAdapter
@RequiredArgsConstructor
public class ExternalBankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmBankingPort {

    private static ExternalFirmBankingMapper externalFirmBankingMapper;

    @Override
    public ExternalBankAccount getBankAccountInfo(ExternalBankAccountRequest request) {
        // 실제 API 구현 불가로 임시 return
        return new ExternalBankAccount(request.getName(), request.getBankAccountNumber(), true);
    }

    @Override
    public FirmBankingResult requestExternalFirmBanking(FirmBankingRequest.FromBankName fromBankName,
                                                        FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
                                                        FirmBankingRequest.ToBankName toBankName,
                                                        FirmBankingRequest.ToBankAccountNumber toBankAccountNumber) {

        // 실제 외부 은행 http 통신 후 펌뱅킹 요청
        externalFirmBankingMapper.mapToDomainRequest(fromBankName, fromBankAccountNumber, toBankName, toBankAccountNumber);

        // 결과 외부 은행의 실제 결과를 firmbanking result로 파싱
        return new FirmBankingResult(0);
    }
}
