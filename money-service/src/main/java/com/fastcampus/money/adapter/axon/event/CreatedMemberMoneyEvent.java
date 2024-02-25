package com.fastcampus.money.adapter.axon.event;

public class CreatedMemberMoneyEvent {
    long id;

    public CreatedMemberMoneyEvent(long targetMembershipId) {
        this.id = targetMembershipId;
    }

    public long getTargetMembershipId() {
        return this.id;
    }
}
