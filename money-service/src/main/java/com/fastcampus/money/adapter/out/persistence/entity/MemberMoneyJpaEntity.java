package com.fastcampus.money.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "money_changing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoneyJpaEntity {

    @Id
    @GeneratedValue
    private long memberMoneyId;

    private long membershipId;

    private int moneyChangingType; //0: 증액, 1: 감액

    private long moneyAmount;

    private long balance;

    public MemberMoneyJpaEntity(long membershipId, long balance) {
        this.membershipId = membershipId;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "MemberMoneyJpaEntity{" +
                "memberMoneyId=" + memberMoneyId +
                ", membershipId=" + membershipId +
                ", moneyChangingType=" + moneyChangingType +
                ", moneyAmount=" + moneyAmount +
                ", balance=" + balance +
                '}';
    }
}
