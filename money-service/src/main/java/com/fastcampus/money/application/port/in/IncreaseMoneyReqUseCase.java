package com.fastcampus.money.application.port.in;

import com.fastcampus.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyReqUseCase {
    MoneyChangingRequest increaseMoney(RequestIncreaseMoneyCommand command);
    MoneyChangingRequest increaseMoneyAsync(RequestIncreaseMoneyCommand command);
    void increaseMoneyRequestByEvent(IncreaseMoneyReqCommand command);
}
