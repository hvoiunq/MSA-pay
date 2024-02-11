package com.fastcampuspay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmBankingRequest {

    @Getter
    private final long firmBankRequestId;
    @Getter
    private final String fromBankName;
    @Getter
    private final String fromBankAccountNumber;
    @Getter
    private final String toBankName;
    @Getter
    private final String toBankAccountNumber;
    @Getter
    private final int moneyAmount;
    @Getter
    private final int firmBankingStatus; // 0: 요청, 1: 완료, 2:실패
    @Getter
    private final UUID uuid;


    public static FirmBankingRequest generateFirmBankingRequest(
            FirmBankingRequest.FirmBankRequestId firmBankRequestId,
            FirmBankingRequest.FromBankName fromBankName,
            FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmBankingRequest.ToBankName toBankName,
            FirmBankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmBankingRequest.MoneyAmount moneyAmount,
            FirmBankingRequest.FirmBankingStatus firmBankingStatus,
            UUID uuid
    ) {
        return new FirmBankingRequest(
                firmBankRequestId.value,
                fromBankName.value,
                fromBankAccountNumber.value,
                toBankName.value,
                toBankAccountNumber.value,
                moneyAmount.value,
                0,
                uuid);
    }

    @Value
    public static class FirmBankRequestId{
        long value;

        public FirmBankRequestId(long value) {
            this.value = value;
        }
    }
    @Value
    public static class FromBankName {
        String value;

        public FromBankName(String value) {
            this.value = value;
        }
    }

    @Value
    public static class FromBankAccountNumber {
        String value;

        public FromBankAccountNumber(String value) {
            this.value = value;
        }
    }

    @Value
    public static class ToBankName {
        String value;

        public ToBankName(String value) {
            this.value = value;
        }
    }

    @Value
    public static class ToBankAccountNumber {
        String value;

        public ToBankAccountNumber(String value) {
            this.value = value;
        }
    }

    @Value
    public static class MoneyAmount {
        int value;

        public MoneyAmount(int value) {
            this.value = value;
        }
    }

    @Value
    public static class FirmBankingStatus {
        int value;

        public FirmBankingStatus(int value) {
            this.value = value;
        }
    }

}
