package com.fastcampus.remittanceservice.adapter.in.web.dto.request;

import com.fastcampus.remittanceservice.application.port.in.RequestRemittanceCommand;
import lombok.Builder;

@Builder
public class RequestRemittanceRequest {
    private long fromMembershipId;
    private long toMembershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int remittanceType; // 0:내부 고객, 1: bank
    private int amount;

    public RequestRemittanceCommand toCommand(RequestRemittanceRequest request) {
        return RequestRemittanceCommand.builder()
                .fromMembershipId(request.fromMembershipId)
                .toMembershipId(request.toMembershipId)
                .toBankName(request.toBankName)
                .toBankAccountNumber(request.toBankAccountNumber)
                .remittanceType(request.remittanceType)
                .amount(request.amount)
                .build();
    }
}
