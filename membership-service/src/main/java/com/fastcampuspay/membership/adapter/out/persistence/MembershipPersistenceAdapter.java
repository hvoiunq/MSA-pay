package com.fastcampuspay.membership.adapter.out.persistence;

import com.fastcampuspay.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fastcampuspay.membership.adapter.out.persistence.entity.SpringDataMembershipRepository;
import com.fastcampuspay.membership.application.port.out.FindMembershipPort;
import com.fastcampuspay.membership.application.port.out.RegisterMembershipPort;
import com.fastcampuspay.membership.domain.Membership;
import com.fastcampuspay.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership createMembership(Membership.MembershipName membershipName,
                                       Membership.MembershipEmail membershipEmail,
                                       Membership.MembershipAddress membershipAddress,
                                       Membership.MembershipIsValid membershipIsValid,
                                       Membership.MembershipIsCorp membershipIsCorp) {

        MembershipJpaEntity entity = membershipRepository.save(
                new MembershipJpaEntity(membershipName.getName(),
                        membershipAddress.getAddress(),
                        membershipEmail.getEmail(),
                        membershipIsValid.isValid(),
                        membershipIsValid.isValid()
                ));

        return membershipMapper.mapToDomainEntity(entity);
    }

    @Override
    public Membership findMembership(Membership.MembershipId membershipId) {
        MembershipJpaEntity entity = membershipRepository.getById(membershipId.getId());
        return membershipMapper.mapToDomainEntity(entity);
    }
}
