package com.fastcampus.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {

    @Getter
    private final long moneyChangingRequestId;
    @Getter
    private final long targetMembershipId;
    @Getter
    private final ChangingType changingType; //enum

    public enum ChangingType {
        INCREASING(0), // 증액
        DECREASING(1) // 감액
        ;
        int code;

        ChangingType(int changingTypeCode) {
            this.code = changingTypeCode;
        }

        public int getChangingType() {
            return this.code;
        }
    }

    @Getter
    private final long changingMoneyAmount; // 증액,감액 요청 금액

    @Getter
    private final ChangingStatus changingMoneyStatus;

    public enum ChangingStatus {
        REQUESTED(0), //요청
        SUCCEEDED(1), //성공
        FAILED(2), //실패
        FAILED_NOT_ENOUGH_MONEY(3),
        FAILED_NOT_EXIST_MEMBERSHIP(4),
        CANCELLED(9) //취소됨
        ;

        private int statusCodeNumber;

        ChangingStatus(int statusCodeNumber) {
            this.statusCodeNumber = statusCodeNumber;
        }

        public int getStatusCodeNumber() {
            return this.statusCodeNumber;
        }
    }

    @Getter
    private final Uuid uuid;

    public static MoneyChangingRequest generateMoneyChangingRequest(MoneyChangingRequestId moneyChangingRequestId,
                                                             TargetMembershipId targetMembershipId,
                                                             ChangingMoneyType changingType,
                                                             ChangingMoneyAmount changingMoneyAmount,
                                                             ChangingMoneyStatus changingMoneyStatus) {
        return new MoneyChangingRequest(
                moneyChangingRequestId.value,
                targetMembershipId.value,
                changingType.value,
                changingMoneyAmount.value,
                changingMoneyStatus.value,
                Uuid.uuid
        );
    }

    @Value
    public static class MoneyChangingRequestId {
        long value;

        public MoneyChangingRequestId(long value) {
            this.value = value;
        }
    }

    @Value
    public static class TargetMembershipId {
        long value;

        public TargetMembershipId(long value) {
            this.value = value;
        }
    }

    @Value
    public static class ChangingMoneyType {
        ChangingType value;

        public ChangingMoneyType(ChangingType value) {
            this.value = value;
        }
    }


    @Value
    public static class ChangingMoneyAmount {
        long value;

        public ChangingMoneyAmount(long value) {
            this.value = value;
        }
    }

    @Value
    public static class ChangingMoneyStatus {
        ChangingStatus value;

        public ChangingMoneyStatus(ChangingStatus value) {
            this.value = value;
        }
    }

    @Value
    public static class Uuid {
        UUID uuid;

        public Uuid() {
            this.uuid = UUID.randomUUID();
        }
    }
}