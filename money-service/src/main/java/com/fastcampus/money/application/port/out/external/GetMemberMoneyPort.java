package com.fastcampus.money.application.port.out.external;

import com.fastcampus.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import com.fastcampus.money.domain.MemberMoney;

public interface GetMemberMoneyPort {
    MemberMoneyJpaEntity getMemberMoney(MemberMoney.MembershipId membershipId);
}
