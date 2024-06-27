package com.api.financialmanager.adapter.out.repository;

import com.api.financialmanager.domain.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
