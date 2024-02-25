package com.fastcampus.money.application.port.in;

import com.fastcampuspay.common.validator.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
public class IncreaseMoneyReqCommand extends SelfValidating<CreateMemberMoneyCommand> {

    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    @NotNull
    private final long membershipId;
    @NotNull
    private final long amount;


    public IncreaseMoneyReqCommand(@NotNull long membershipId, long amount) {
        this.membershipId = membershipId;
        this.amount = amount;

        this.validateSelf();
    }
}
