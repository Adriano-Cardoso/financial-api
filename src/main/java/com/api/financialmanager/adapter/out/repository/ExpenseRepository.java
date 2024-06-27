package com.api.financialmanager.adapter.out.repository;

import com.api.financialmanager.domain.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
