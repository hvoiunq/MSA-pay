package com.fastcampuspay.banking.adapter.out.external.bank;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalBankAccountRequest {
    private String name;

    private String bankAccountNumber;

    private boolean isValid;
}
