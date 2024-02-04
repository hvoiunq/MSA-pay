package com.fastcampuspay.membership.application.port.in;

import com.fastcampuspay.common.validator.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)  //super까지 포함해서 hashing할건지
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {
    @NotNull private long membershipId;

    public FindMembershipCommand(long membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
