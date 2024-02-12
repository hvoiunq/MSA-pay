package com.fastcampus.money.adapter.in.web.dto.request;

import com.fastcampus.money.application.port.in.RequestIncreaseMoneyCommand;

public record IncreaseMoneyChangingRequest (
        long targetMembershipId,
        long amount

){
    public RequestIncreaseMoneyCommand toCommand(IncreaseMoneyChangingRequest request) {
        return RequestIncreaseMoneyCommand.builder()
                .targetMembershipId(request.targetMembershipId)
                .amount(request.amount)
                .build();
    }
}

