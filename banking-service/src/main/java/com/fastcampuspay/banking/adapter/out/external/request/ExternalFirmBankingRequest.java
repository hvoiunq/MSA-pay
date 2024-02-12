package com.fastcampuspay.banking.adapter.out.external.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public record ExternalFirmBankingRequest(
        String fromBankName,
        String fromBankAccountNumber,
        String toBankName,
        String toBankAccountNumber
) {
    public static ExternalFirmBankingRequest of(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber) {
        return new ExternalFirmBankingRequest(fromBankName, fromBankAccountNumber, toBankName, toBankAccountNumber);
    }
}


