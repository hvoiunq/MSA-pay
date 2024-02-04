package com.fastcampuspay.membership.adapter.out.persistence.entity;

import com.fastcampuspay.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMembershipRepository extends JpaRepository<MembershipJpaEntity, Long> {

}
