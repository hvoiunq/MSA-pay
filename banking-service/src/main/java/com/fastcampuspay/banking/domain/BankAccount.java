package com.fastcampuspay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BankAccount {

    private final long bankAccountId;
    private final long membershipId;
    private final String bankName; //enum
    private final String bankAccountNumber;
    private final boolean linkedStatusIsValid;

    /**
     * @param bankAccountId
     * @param membershipId
     * @param bankName
     * @param bankAccountNumber
     * @param linkedStatusIsValid
     * @return
     */
    public static BankAccount generateBankAccount(
            BankAccount.BankAccountId bankAccountId,
            BankAccount.MembershipId membershipId,
            BankAccount.BankName bankName,
            BankAccount.BankAccountNumber bankAccountNumber,
            BankAccount.LinkedStatusIsValid linkedStatusIsValid
    ) {
        return new BankAccount(
                bankAccountId.value,
                membershipId.value,
                bankName.value,
                bankAccountNumber.value,
                linkedStatusIsValid.value);
    }

    @Value
    public static class BankAccountId {
        long value;

        public BankAccountId(long value) {
            this.value = value;
        }
    }

    @Value
    public static class MembershipId {
        long value;

        public MembershipId(long value) {
            this.value = value;
        }
    }

    @Value
    public static class BankName {
        String value;

        public BankName(String value) {
            this.value = value;
        }
    }

    @Value
    public static class BankAccountNumber {
        String value;

        public BankAccountNumber(String value) {
            this.value = value;
        }
    }

    @Value
    public static class LinkedStatusIsValid {
        boolean value;

        public LinkedStatusIsValid(boolean value) {
            this.value = value;
        }
    }

}