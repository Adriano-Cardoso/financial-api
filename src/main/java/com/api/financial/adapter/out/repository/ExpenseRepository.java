package com.api.financial.adapter.out.repository;

import com.api.financial.domain.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
