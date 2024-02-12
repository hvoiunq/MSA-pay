package com.fastcampuspay.banking.application.port.out.external;

public interface GetMembershipPort {

    public MembershipStatus getMembership(String membershipId);
}
