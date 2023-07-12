package com.financial.financeapp.entities.impl;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.entities.Occurrence;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "tb_income")
public class Income extends Occurrence implements Serializable {
    private static final long serialVersionUID = 1L;

    public Income(Long id, Double amount, LocalDate date, Account account, Type type, Category category, String description) {
        super();
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.account = account;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public Income() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Income income = (Income) o;

        if (!id.equals(income.id)) return false;
        if (!amount.equals(income.amount)) return false;
        return date.equals(income.date);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
