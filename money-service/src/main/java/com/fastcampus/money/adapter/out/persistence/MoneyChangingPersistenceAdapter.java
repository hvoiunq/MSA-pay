package com.fastcampus.money.adapter.out.persistence;

import com.fastcampus.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import com.fastcampus.money.adapter.out.persistence.entity.MemberMoneyRepository;
import com.fastcampus.money.adapter.out.persistence.entity.MoneyChangingJpaEntity;
import com.fastcampus.money.adapter.out.persistence.entity.SpringDataMoneyChangingRepository;
import com.fastcampus.money.application.port.out.external.GetMemberMoneyPort;
import com.fastcampus.money.application.port.out.persistence.CreateMemberMoneyPort;
import com.fastcampus.money.application.port.out.persistence.MoneyChangingPort;
import com.fastcampus.money.domain.MemberMoney;
import com.fastcampus.money.domain.MoneyChangingRequest;
import com.fastcampuspay.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MoneyChangingPersistenceAdapter implements MoneyChangingPort, CreateMemberMoneyPort, GetMemberMoneyPort {

    private final SpringDataMoneyChangingRepository moneyChangingRepository;
    private final MoneyChangingRequestMapper moneyChangingRequestMapper;
    private final MemberMoneyRepository memberMoneyRepository;

    @Override
    public MoneyChangingRequest saveMoneyChangingRequest(MoneyChangingRequest.TargetMembershipId targetMembershipId,
                                                         MoneyChangingRequest.ChangingMoneyType changingType,
                                                         MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
                                                         MoneyChangingRequest.ChangingMoneyStatus changingMoneyStatus,
                                                         UUID uuid) {
        // entity 저장
        MoneyChangingJpaEntity saved = moneyChangingRepository.save(
                new MoneyChangingJpaEntity(
                        targetMembershipId.getValue(),
                        changingType.getValue().getChangingType(),
                        changingMoneyAmount.getValue(),
                        new Date(System.currentTimeMillis()),
                        changingMoneyStatus.getValue().getStatusCodeNumber(),
                        uuid
                )
        );
        // domain object mapper

        return moneyChangingRequestMapper.mapToMoneyChangingEntity(saved);
    }

    @Override
    public MemberMoney increaseMoney(MemberMoney.MembershipId membershipId, MemberMoney.Balance balance) {
        MemberMoneyJpaEntity entity;

        try {
            List<MemberMoneyJpaEntity> jpaEntities = memberMoneyRepository.findByMembershipId(membershipId.getValue());
            entity = jpaEntities.get(0);
            entity.setBalance(entity.getBalance() + balance.getValue());
        } catch (Exception e) {
            MemberMoneyJpaEntity memberMoneyJpaEntity = new MemberMoneyJpaEntity(membershipId.getValue(), balance.getValue());
            return moneyChangingRequestMapper.mapToMemberMoneyEntity(memberMoneyJpaEntity);
        }
        MemberMoneyJpaEntity saved = memberMoneyRepository.save(entity);
        return moneyChangingRequestMapper.mapToMemberMoneyEntity(saved);
    }


    @Override
    public void createMemberMoney(MemberMoney.MembershipId memberId, MemberMoney.MoneyAggregateIdentifier aggregateIdentifier) {
        MemberMoneyJpaEntity memberMoneyJpaEntity = new MemberMoneyJpaEntity(
                memberId.getValue(),
                0,
                aggregateIdentifier.getAggregateIdentifier()
        );
        memberMoneyRepository.save(memberMoneyJpaEntity);
    }


    @Override
    public MemberMoneyJpaEntity getMemberMoney(MemberMoney.MembershipId membershipId) {
        Optional<MemberMoneyJpaEntity> byId = memberMoneyRepository.findById(membershipId.getValue());
        return byId.get();
    }
}
