package com.fastcampuspay.membership.adapter.in.web.dto.response;

import com.fastcampuspay.membership.domain.Membership;
import lombok.Builder;
import lombok.Getter;

@Builder
public record MembershipResponse(
        Long id,
        String name,
        String address,
        String email,
        boolean isValid,
        boolean isCorp

) {
    public static MembershipResponse of(Membership membership) {
        return MembershipResponse.builder()
                .id(membership.getId().getId())
                .name(membership.getName().getName())
                .email(membership.getEmail().getEmail())
                .address(membership.getAddress().getAddress())
                .isValid(membership.getIsValid().isValid())
                .isCorp(membership.getIsCorp().isCorp())
                .build();
    }

}
