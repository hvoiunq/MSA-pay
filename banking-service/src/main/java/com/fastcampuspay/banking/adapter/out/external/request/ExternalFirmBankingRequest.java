package com.fastcampuspay.banking.adapter.out.external.request;


import lombok.Getter;

public record ExternalFirmBankingRequest(

        @Getter
        String fromBankName,
        @Getter
        String fromBankAccountNumber,
        @Getter
        String toBankName,
        @Getter
        String toBankAccountNumber
) {
    public static ExternalFirmBankingRequest of(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber) {
        return new ExternalFirmBankingRequest(fromBankName, fromBankAccountNumber, toBankName, toBankAccountNumber);
    }
}


