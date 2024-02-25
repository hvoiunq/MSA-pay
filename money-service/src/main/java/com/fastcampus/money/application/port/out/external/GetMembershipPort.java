package com.fastcampus.money.application.port.out.external;

public interface GetMembershipPort {

    public MembershipStatus getMembership(long membershipId);
}
