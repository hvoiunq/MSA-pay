package com.fastcampuspay.banking.application.port.out;

import com.fastcampuspay.banking.domain.BankAccount;


public interface RegisterBankAccountPort {

    BankAccount createBankAccount(
            BankAccount.BankAccountId bankAccountId
            , BankAccount.MembershipId membershipId
            , BankAccount.BankName bankName
            , BankAccount.BankAccountNumber bankAccountNumber
            , BankAccount.LinkedStatusIsValid linkedStatusIsValid
    );
}
