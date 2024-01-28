package com.fastcampuspay.membership.adapter.in.web.dto.request;

import com.fastcampuspay.membership.application.port.in.RegisterMembershipCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMembershipRequest {
    private String name;
    private String address;
    private String email;
    private boolean isCorp;

    public RegisterMembershipCommand toCommand() {
        return RegisterMembershipCommand.builder()
                .name(this.getName())
                .address(this.getAddress())
                .email(this.getEmail())
                .isValid(true)
                .isCorp(this.isCorp())
                .build();
    }
}

