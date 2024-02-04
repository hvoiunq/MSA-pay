package com.fastcampuspay.membership.application.port.in;

import com.fastcampuspay.common.validator.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipCommand> {

    @NotNull
    private final long id;
    @NotNull
    private final String name;
    @NotNull
    private final String email;
    @NotBlank
    private final String address; // (참고) 빈값 방지
    @AssertTrue(message = "User must be active")
    private final boolean isValid;
    @NotNull
    private final boolean isCorp;

    public ModifyMembershipCommand(long id, String name, String email, String address, boolean isValid, boolean isCorp) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorp = isCorp;

        this.validateSelf();
    }
}
