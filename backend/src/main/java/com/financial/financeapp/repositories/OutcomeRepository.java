package com.financial.financeapp.repositories;

import com.financial.financeapp.entities.impl.Income;
import com.financial.financeapp.entities.impl.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutcomeRepository extends JpaRepository<Outcome, Long> {

    @Query("SELECT o FROM Outcome o WHERE YEAR(o.date) = :year AND MONTH(o.date) = :month")
    List<Outcome> findByMonthAndYear(int month, int year);
}
