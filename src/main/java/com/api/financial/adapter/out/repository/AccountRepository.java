package com.api.financial.adapter.out.repository;

import com.api.financial.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByTransactionId(String transactionId);
}
