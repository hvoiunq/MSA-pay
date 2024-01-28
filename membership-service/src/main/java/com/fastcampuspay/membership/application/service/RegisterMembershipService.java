package com.fastcampuspay.membership.application.service;

import com.fastcampuspay.common.annotation.UseCase;
import com.fastcampuspay.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fastcampuspay.membership.adapter.out.persistence.MembershipMapper;
import com.fastcampuspay.membership.application.port.in.RegisterMembershipCommand;
import com.fastcampuspay.membership.application.port.in.RegisterMembershipUseCase;
import com.fastcampuspay.membership.application.port.out.RegisterMembershipPort;
import com.fastcampuspay.membership.domain.Membership;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        // command -> DB 통신
        // DB통신 결과 return

        // production code logic -> DB
        // external system => port, adapter 가 있어야만 통신이 가능하다.

        MembershipJpaEntity membershipJpaEntity = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );

        // entity -> 변환

        return membershipMapper.mapToDomainEntity(membershipJpaEntity);

    }
}
