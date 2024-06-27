package com.api.financial.adapter.out.repository;

import com.api.financial.domain.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
