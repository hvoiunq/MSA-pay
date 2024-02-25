package com.fastcampus.money.adapter.in.web.dto.request;

import com.fastcampus.money.application.port.in.RequestIncreaseMoneyCommand;
import lombok.Builder;

@Builder
public record CreateMemberMoneyRequest(
        long targetMembershipId

){
}

