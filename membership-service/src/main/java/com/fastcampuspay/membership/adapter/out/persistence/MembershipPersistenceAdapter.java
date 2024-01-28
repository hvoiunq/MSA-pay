package com.fastcampuspay.membership.adapter.out.persistence;

import com.fastcampuspay.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fastcampuspay.membership.application.port.out.RegisterMembershipPort;
import com.fastcampuspay.membership.domain.Membership;
import com.fastcampuspay.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;
    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {

        MembershipJpaEntity savedMembership = membershipRepository.save(new MembershipJpaEntity(
                membershipName.getName(),
                membershipAddress.getAddress(),
                membershipEmail.getEmail(),
                membershipIsValid.isValid(),
                membershipIsValid.isValid()
        ));

        return savedMembership;

    }
}
