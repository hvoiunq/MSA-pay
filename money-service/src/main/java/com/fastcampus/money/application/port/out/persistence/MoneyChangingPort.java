package com.fastcampus.money.application.port.out.persistence;

import com.fastcampus.money.domain.MemberMoney;
import com.fastcampus.money.domain.MoneyChangingRequest;

import java.util.UUID;


public interface MoneyChangingPort {

    MoneyChangingRequest saveMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.ChangingMoneyType changingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.ChangingMoneyStatus changingMoneyStatus,
            UUID uuid
    );

    MemberMoney increaseMoney(
            MemberMoney.MembershipId membershipId,
            MemberMoney.Balance balance
            );

}
