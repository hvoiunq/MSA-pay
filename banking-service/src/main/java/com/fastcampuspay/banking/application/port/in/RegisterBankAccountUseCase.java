package com.fastcampuspay.banking.application.port.in;

import com.fastcampuspay.banking.domain.BankAccount;

public interface RegisterBankAccountUseCase {
    BankAccount registerBankAccount(RegisterBankAccountCommand command);
}
