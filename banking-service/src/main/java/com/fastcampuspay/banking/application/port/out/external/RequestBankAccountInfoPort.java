package com.fastcampuspay.banking.application.port.out.external;

import com.fastcampuspay.banking.adapter.out.external.request.ExternalBankAccountRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.ExternalBankAccount;

public interface RequestBankAccountInfoPort {
    ExternalBankAccount getBankAccountInfo(ExternalBankAccountRequest request);
}
