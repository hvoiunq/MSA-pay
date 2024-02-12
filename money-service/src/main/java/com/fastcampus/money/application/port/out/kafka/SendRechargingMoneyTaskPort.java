package com.fastcampus.money.application.port.out.kafka;

import com.fastcampuspay.common.task.RechargingMoneyTask;

public interface SendRechargingMoneyTaskPort {
    void sendRechargingMoneyTaskPort(RechargingMoneyTask rechargingMoneyTask);
}
