package com.fastcampus.remittanceservice.adapter.in.web.dto.response;

import com.fastcampus.money.domain.MoneyChangingRequest;

public class MoneyChangingResDetail {
    private long moneyChangingRequestId;
    private MoneyChangingRequest.ChangingType moneyChangingType; //enum 0:증액, 1: 감액
    private long amount;
    private MoneyChangingRequest.ChangingStatus resultStatus;

    public MoneyChangingResDetail(long moneyChangingRequestId, MoneyChangingRequest.ChangingType moneyChangingType, long amount, MoneyChangingRequest.ChangingStatus resultStatus) {
        this.moneyChangingRequestId = moneyChangingRequestId;
        this.moneyChangingType = moneyChangingType;
        this.amount = amount;
        this.resultStatus = resultStatus;
    }
}
