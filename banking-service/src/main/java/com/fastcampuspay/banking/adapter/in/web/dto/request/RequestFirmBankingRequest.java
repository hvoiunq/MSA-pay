package com.fastcampuspay.banking.adapter.in.web.dto.request;

import com.fastcampuspay.banking.application.port.in.RequestFirmBankingCommand;

public record RequestFirmBankingRequest (
    // a -> b 실물계좌 요청 Request

    String fromBankName,
    String fromBankAccountNumber,
    String toBankName,
    String toBankAccountNumber,
    int moneyAccount
) {
    public RequestFirmBankingCommand toCommand(RequestFirmBankingRequest request) {
        return new RequestFirmBankingCommand(
                request.fromBankName,
                request.fromBankAccountNumber,
                request.toBankName,
                request.toBankAccountNumber,
                request.moneyAccount
        );
    }
}
