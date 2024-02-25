package com.fastcampus.money.application.port.in;

import com.fastcampuspay.common.validator.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)  //super까지 포함해서 hashing할건지
public class CreateMemberMoneyCommand extends SelfValidating<CreateMemberMoneyCommand> {
    @NotNull
    private final long targetMembershipId;
    public CreateMemberMoneyCommand(@NotNull long targetMembershipId) {
        this.targetMembershipId = targetMembershipId;

        this.validateSelf();
    }
}
