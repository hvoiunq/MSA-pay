package com.fastcampus.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoney {
    @Getter
    private final long memberMoneyId;

    @Getter
    private final long membershipId;

    @Getter
    private final long balance; // 잔액

    public static MemberMoney generateMemberMoney(
            MemberMoneyId memberMoneyId,
            MembershipId membershipId,
            Balance balance) {
        return new MemberMoney(
                memberMoneyId.value,
                membershipId.value,
                balance.value
        );
    }

    @Value
    public static class MemberMoneyId {
        long value;

        public MemberMoneyId(long value) {
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
    public static class Balance {
        long value;

        public Balance(long value) {
            this.value = value;
        }
    }

    @Value
    public static class MoneyAggregateIdentifier {
        String aggregateIdentifier;
        public MoneyAggregateIdentifier(String aggregateIdentifier) {
            this.aggregateIdentifier = aggregateIdentifier;
        }
    }
}
