package com.fastcampus.remittanceservice.adapter.out.persistence;

import com.fastcampus.money.adapter.in.web.dto.response.MoneyChangingResDetail;
import com.fastcampus.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import com.fastcampus.money.adapter.out.persistence.entity.MoneyChangingJpaEntity;
import com.fastcampus.money.domain.MemberMoney;
import com.fastcampus.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingRequestMapper {

    public MoneyChangingResDetail mapToMoneyChangingResDetail(MoneyChangingRequest moneyChangingRequest) {
        return new MoneyChangingResDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                moneyChangingRequest.getChangingType(),
                moneyChangingRequest.getChangingMoneyAmount(),
                moneyChangingRequest.getChangingMoneyStatus()
        );
    }

    public MoneyChangingRequest mapToMoneyChangingEntity(MoneyChangingJpaEntity moneyChangingJpaEntity) {
        return MoneyChangingRequest.generateMoneyChangingRequest(
                new MoneyChangingRequest.MoneyChangingRequestId(moneyChangingJpaEntity.getMoneyChangingRequestId()),
                new MoneyChangingRequest.TargetMembershipId(moneyChangingJpaEntity.getTargetMembershipId()),
                new MoneyChangingRequest.ChangingMoneyType(moneyChangingJpaEntity.getMoneyChangingType()),
                new MoneyChangingRequest.ChangingMoneyAmount(moneyChangingJpaEntity.getMoneyAmount()),
                new MoneyChangingRequest.ChangingMoneyStatus(moneyChangingJpaEntity.getMoneyChangingStatus())
        );
    }

    public MemberMoney mapToMemberMoneyEntity(MemberMoneyJpaEntity memberMoneyJpaEntity) {
        return MemberMoney.generateMemberMoney(
                new MemberMoney.MemberMoneyId(memberMoneyJpaEntity.getMemberMoneyId()),
                new MemberMoney.MembershipId(memberMoneyJpaEntity.getMembershipId()),
                new MemberMoney.Balance(memberMoneyJpaEntity.getBalance())
        );
    }
}
