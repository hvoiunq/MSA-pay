package com.fastcampuspay.banking.adapter.in.web.dto.request;

import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;

public record RegisterBankAccountRequest (
        long membershipId,
        String bankName,
        String bankAccountNumber,
        boolean isValid

){
    public RegisterBankAccountCommand toCommand() {
        return RegisterBankAccountCommand.builder()
                .membershipId(this.membershipId)
                .bankName(this.bankName)
                .bankAccountNumber(this.bankAccountNumber)
                .isValid(this.isValid)
                .build();
    }
}

