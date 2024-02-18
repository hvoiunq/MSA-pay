package com.fastcampus.remittanceservice.application.port.out.membership;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipStatus {
    private long membershipId;
    private boolean isValid;
}
