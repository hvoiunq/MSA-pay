package com.fastcampuspay.banking.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_account")
@Data
@NoArgsConstructor
public class BankAccountJpaEntity {

    @Id
    @GeneratedValue
    private long bankAccountId;
    private long membershipId;
    private String bankName; //enum
    private String bankAccountNumber;
    private boolean linkedStatusIsValid;

    public BankAccountJpaEntity(long membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;
    }

    @Override
    public String toString() {
        return "BankAccountJpaEntity{" +
                "bankAccountId=" + bankAccountId +
                ", membershipId=" + membershipId +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", linkedStatusIsValid=" + linkedStatusIsValid +
                '}';
    }
}
