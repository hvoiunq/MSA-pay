package com.fastcampuspay.banking.adapter.out.persistence.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataBankAccountRepository extends JpaRepository<BankAccountJpaEntity, Long> {

}
