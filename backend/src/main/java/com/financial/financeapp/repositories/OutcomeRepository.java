package com.financial.financeapp.repositories;

import com.financial.financeapp.entities.impl.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutcomeRepository extends JpaRepository<Outcome, Long> {
}
