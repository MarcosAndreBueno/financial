package com.financial.financeapp.repositories;

import com.financial.financeapp.entities.impl.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT i FROM Income i WHERE YEAR(i.date) = :year AND MONTH(i.date) = :month")
    List<Income> findByMonthAndYear(int month, int year);

}
