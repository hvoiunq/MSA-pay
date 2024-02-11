package com.fastcampuspay.banking.adapter.in.web.dto.request;

import com.fastcampuspay.banking.application.port.in.RequestFirmBankingCommand;
import lombok.Builder;
import lombok.Data;

@Builder
public class RequestFirmBankingRequest {
    // a -> b 실물계좌 요청 Request

    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAccount;

    public RequestFirmBankingCommand toCommand(RequestFirmBankingRequest request) {
        new RequestFirmBankingCommand(
                request.fromBankName,
                request.fromBankAccountNumber,
                request.toBankName,
                request.toBankAccountNumber,
                request.moneyAccount
        );
    }
}
