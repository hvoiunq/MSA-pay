package com.fastcampuspay.banking.application.port.out;

import com.fastcampuspay.banking.adapter.out.external.bank.ExternalBankAccountRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.ExternalBankAccount;

public interface RequestBankAccountInfoPort {
    ExternalBankAccount getBankAccountInfo(ExternalBankAccountRequest request);
}
