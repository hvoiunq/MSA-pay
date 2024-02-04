package com.fastcampuspay.banking.adapter.in.web.dto.response;

import com.fastcampuspay.banking.domain.BankAccount;
import lombok.Builder;

@Builder
public record BankAccountResponse(
        Long bankAccountId,
        Long membershipId,
        String bankName,
        String bankAccountNumber,
        boolean isValid
) {
    public static BankAccountResponse of(BankAccount bankAccount) {
        return BankAccountResponse.builder()
                .bankAccountId(bankAccount.getBankAccountId())
                .membershipId(bankAccount.getMembershipId())
                .bankName(bankAccount.getBankName())
                .bankAccountNumber(bankAccount.getBankAccountNumber())
                .isValid(bankAccount.isLinkedStatusIsValid())
                .build();
    }
}
