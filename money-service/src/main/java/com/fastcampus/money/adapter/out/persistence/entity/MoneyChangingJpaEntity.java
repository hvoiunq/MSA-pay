package com.fastcampus.money.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "money_changing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChangingJpaEntity {

    @Id
    @GeneratedValue
    private long moneyChangingRequestId;

    private long targetMembershipId;

    private int moneyChangingType; //0: 증액, 1: 감액

    private long moneyAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private int moneyChangingStatus; //0: 요청, 1: 성공, 2: 실패
    private UUID uuid;

    public MoneyChangingJpaEntity(long targetMembershipId, int moneyChangingType, long moneyAmount, Date timestamp, int moneyChangingStatus, UUID uuid) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingType = moneyChangingType;
        this.moneyAmount = moneyAmount;
        this.timestamp = timestamp;
        this.moneyChangingStatus = moneyChangingStatus;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "MoneyChangingJpaEntity{" +
                "moneyChangingRequestId=" + moneyChangingRequestId +
                ", targetMembershipId=" + targetMembershipId +
                ", moneyChangingType=" + moneyChangingType +
                ", moneyAmount=" + moneyAmount +
                ", timestamp=" + timestamp +
                ", moneyChangingStatus=" + moneyChangingStatus +
                '}';
    }
}
