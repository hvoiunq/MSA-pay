package com.fastcampuspay.membership.adapter.out.persistence;

import com.fastcampuspay.common.annotation.PersistenceAdapter;
import com.fastcampuspay.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fastcampuspay.membership.adapter.out.persistence.entity.SpringDataMembershipRepository;
import com.fastcampuspay.membership.application.port.out.FindMembershipPort;
import com.fastcampuspay.membership.application.port.out.ModifyMembershipPort;
import com.fastcampuspay.membership.application.port.out.RegisterMembershipPort;
import com.fastcampuspay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

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
                        membershipIsCorp.isCorp()
                ));

        return membershipMapper.mapToDomainEntity(entity);
    }

    @Override
    public Membership findMembership(Membership.MembershipId membershipId) {
        MembershipJpaEntity entity = membershipRepository.getById(membershipId.getId());
        return membershipMapper.mapToDomainEntity(entity);
    }

    @Override
    public Membership modifyMembership(Membership.MembershipId membershipId, Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        MembershipJpaEntity entity = membershipRepository.getById(membershipId.getId());
        entity.setName(membershipName.getName());
        entity.setEmail(membershipEmail.getEmail());
        entity.setAddress(membershipAddress.getAddress());
        entity.setValid(membershipIsValid.isValid());
        entity.setCorp(membershipIsCorp.isCorp());

        MembershipJpaEntity savedEntity = membershipRepository.save(entity);

        return membershipMapper.mapToDomainEntity(savedEntity);
    }
}
