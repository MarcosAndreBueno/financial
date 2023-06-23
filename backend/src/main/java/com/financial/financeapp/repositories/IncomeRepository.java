package com.financial.financeapp.repositories;

import com.financial.financeapp.entities.impl.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
