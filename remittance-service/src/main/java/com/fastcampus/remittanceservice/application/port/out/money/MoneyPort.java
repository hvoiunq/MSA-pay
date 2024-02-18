package com.fastcampus.remittanceservice.application.port.out.money;

//송금 서비스에서 필요한 머니 정보에 한정
public interface MoneyPort {

    MoneyInfo getMoneyInfo(String membershipId);

    boolean requestMoneyRecharging(String membershipId, int amount);
    boolean requestChangingMoneyIncreasing(String membershipId, int amount);
    boolean requestChangingMoneyDecreasing(String membershipId, int amount);
}
