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
public class RequestIncreaseMoneyCommand extends SelfValidating<RequestIncreaseMoneyCommand> {
    @NotNull
    private final long targetMembershipId;
    @NotBlank
    private final long amount; // (참고) 빈값 방지

    public RequestIncreaseMoneyCommand(long targetMembershipId, long amount) {
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;

        this.validateSelf();
    }
}
