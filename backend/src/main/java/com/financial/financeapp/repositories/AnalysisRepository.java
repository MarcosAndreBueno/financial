package com.financial.financeapp.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.financial.financeapp.entities.impl.Outcome;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AnalysisRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Outcome> getOucomeByYearMonthDay(int year, int month, int maxDay) {
        String jpql = "SELECT o FROM Outcome o " +
                "WHERE YEAR(o.date) = :year AND " +
                "MONTH(o.date) = :month AND " +
                "DAY(o.date) <= :day";

        return entityManager.createQuery(jpql, Outcome.class)
                .setParameter("year", year)
                .setParameter("month", month)
                .setParameter("day", maxDay)
                .getResultList();
    }
}
