package com.fastcampus.remittanceservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Remittance { // 송금 요청에 대한 상태

    @Getter
    private final long remittanceRequestId;
    @Getter
    private final long remittanceFromMembershipId;
    @Getter
    private long toMembershipId;
    @Getter
    private String toBankName;
    @Getter
    private String toBankAccountNumber;
    @Getter
    private int remittanceType; // 0:내부 고객, 1: bank
    @Getter
    private int amount;
    @Getter
    private String remittanceStatus;

    @Value
    public static class RemittanceRequestId {
        long value;
        public RemittanceRequestId(long value) {
            this.value = value;
        }
    }
    @Value
    public static class RemittanceFromMembershipId {
        long value;
        public RemittanceFromMembershipId(long value) {
            this.value = value;
        }
    }
    @Value
    public static class ToMembershipId {
        long value;
        public ToMembershipId(long value) {
            this.value = value;
        }
    }
    @Value
    public static class ToBankName {
        String  value;
        public ToBankName(String value) {
            this.value = value;
        }
    }
    @Value
    public static class RemittanceType {
        String  value;
        public RemittanceType(String value) {
            this.value = value;
        }
    }
    @Value
    public static class Amount {
        int  value;
        public Amount(int value) {
            this.value = value;
        }
    }
    @Value
    public static class RemittanceStatus {
        int  value;
        public RemittanceStatus(int value) {
            this.value = value;
        }
    }

}