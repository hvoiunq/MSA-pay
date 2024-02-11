package com.fastcampuspay.banking.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "request_firmbanking")
@Data
@NoArgsConstructor
public class RequestFirmBankingJpaEntity {

    @Id
    @GeneratedValue
    private long requestFirmBankingId;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAccount;
    private int status;
    private UUID uuid;

    public RequestFirmBankingJpaEntity(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int moneyAccount, int status, UUID uuid) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAccount = moneyAccount;
        this.status = status;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "RequestFirmBankingJpaEntity{" +
                "fromBankName='" + fromBankName + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                ", toBankName='" + toBankName + '\'' +
                ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
                ", moneyAccount=" + moneyAccount +
                ", status=" + status +
                '}';
    }
}
