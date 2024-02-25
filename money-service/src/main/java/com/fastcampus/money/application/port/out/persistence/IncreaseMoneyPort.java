package com.fastcampus.money.application.port.out.persistence;

import com.fastcampus.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import com.fastcampus.money.adapter.out.persistence.entity.MoneyChangingJpaEntity;
import com.fastcampus.money.domain.MemberMoney;
import com.fastcampus.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyPort {
    MoneyChangingJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.ChangingMoneyType moneyChangingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.ChangingMoneyStatus moneyChangingStatus,
            MoneyChangingRequest uuid
    );

    MemberMoneyJpaEntity increaseMoney(
            MemberMoney.MembershipId memberId,
            long increaseMoneyAmount
    );
}
