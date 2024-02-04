package com.fastcampuspay.banking.adapter.out.external.bank;

public class ExternalBankAccount {
    private String bankName;
    private String accountNumber;
    private boolean isValid;

    public ExternalBankAccount(String bankName, String accountNumber, boolean isValid) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.isValid = isValid;
    }
}
