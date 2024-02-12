package com.fastcampus.money.adapter.out.persistence.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMoneyChangingRepository extends JpaRepository<MoneyChangingJpaEntity, Long> {

}
