package com.fastcampus.money.adapter.axon.command;

import com.fastcampuspay.common.validator.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)  //super까지 포함해서 hashing할건지
public class CreatedMemberMoneyCommand extends SelfValidating<CreatedMemberMoneyCommand> {
    @NotNull
    private final long targetMembershipId;
    public CreatedMemberMoneyCommand(@NotNull long targetMembershipId) {
        this.targetMembershipId = targetMembershipId;

        this.validateSelf();
    }
}
