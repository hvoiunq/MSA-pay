package com.fastcampus.money.adapter.out.persistence.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {

    @Query("SELECT e FROM MemberMoneyJpaEntity e WHERE e.membershipId = :membershipId")
    List<MemberMoneyJpaEntity> findByMembershipId(@Param("membershipId") Long membershipId);
}
