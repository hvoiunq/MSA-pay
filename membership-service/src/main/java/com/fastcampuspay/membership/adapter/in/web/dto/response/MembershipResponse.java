package com.fastcampuspay.membership.adapter.in.web.dto.response;

import com.fastcampuspay.membership.domain.Membership;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MembershipResponse {

    private Long id;
    private String name;
    private String address;
    private String email;
    private boolean isValid;
    private boolean isCorp;

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
