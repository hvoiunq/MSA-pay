package com.fastcampuspay.membership.adapter.in.web.dto.request;

import com.fastcampuspay.membership.application.port.in.ModifyMembershipCommand;
import com.fastcampuspay.membership.exception.NoMatchIdException;

public record ModifyMembershipRequest(
        long id,
        String name,
        String address,
        String email,
        boolean isValid,
        boolean isCorp
) {
    public ModifyMembershipCommand toCommand(Long membershipId) {
        if (!membershipId.equals(this.id)) {
            throw new NoMatchIdException();
        }

        return ModifyMembershipCommand.builder()
                .id(this.id)
                .name(this .name)
                .address(this.address)
                .email(this.email)
                .isValid(this.isValid)
                .isCorp(this.isCorp)
                .build();
    }
}
