package com.fastcampuspay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {

    @Getter private final MembershipId id;
    @Getter private final MembershipName name;
    @Getter private final MembershipEmail email;
    @Getter private final MembershipAddress address;
    @Getter private final MembershipIsValid isValid;
    @Getter private final MembershipIsCorp isCorp;

    // Membership : 오염이 되면 안되는 클래스, 고객정보, 핵심 도메인

    public static Membership generateMember(
            MembershipId id,
            MembershipName name,
            MembershipEmail email,
            MembershipAddress address,
            MembershipIsValid isValid,
            MembershipIsCorp isCorp
    ) {
        return new Membership(
                id,
                name,
                email,
                address,
                isValid,
                isCorp);
    }

    @Value
    public static class MembershipId {
        long id;

        public MembershipId(Long value) {
            this.id = value;
        }
    }

    @Value
    public static class MembershipName {
        String name;

        public MembershipName(String value) {
            this.name = value;
        }
    }

    @Value
    public static class MembershipEmail {
        String email;

        public MembershipEmail(String value) {
            this.email = value;
        }
    }

    @Value
    public static class MembershipAddress {
        String address;

        public MembershipAddress(String value) {
            this.address = value;
        }
    }

    @Value
    public static class MembershipIsValid {
        boolean isValid;

        public MembershipIsValid(boolean value) {
            this.isValid = value;
        }
    }

    @Value
    public static class MembershipIsCorp {
        boolean isCorp;

        public MembershipIsCorp(boolean value) {
            this.isCorp = value;
        }
    }

}
