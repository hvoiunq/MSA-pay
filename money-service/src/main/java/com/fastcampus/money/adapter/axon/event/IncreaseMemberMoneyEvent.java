package com.fastcampus.money.adapter.axon.event;

import jakarta.validation.constraints.NotNull;

public class IncreaseMemberMoneyEvent {
    String aggregateIdentifier;
    long membershipId;
    long amount;

    public IncreaseMemberMoneyEvent(String id, long targetMembershipId, @NotNull long amount) {
        this.aggregateIdentifier = id;
        this.membershipId = targetMembershipId;
        this.amount = amount;
    }

    public long getTargetMembershipId() {
        return this.membershipId;
    }
}
