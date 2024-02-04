package com.fastcampuspay.banking.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountRequest {
    private String name;

    private String address;

    private String email;
}
