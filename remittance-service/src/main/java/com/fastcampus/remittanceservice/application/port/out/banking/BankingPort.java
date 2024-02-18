package com.fastcampus.remittanceservice.application.port.out.banking;

public interface BankingPort {

    BankingInfo  getBankingAccountInfo(String bankName, String bankAccountNumber);

    boolean requestFirmBanking(String bankName, String bankAccountNumber, int amount);
}
