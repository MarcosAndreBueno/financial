package com.financial.financeapp.entities.impl;

import com.financial.financeapp.entities.Ocurrence;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "tb_income")
public class Income extends Ocurrence implements Serializable {
    private static final long serialVersionUID = 1L;

    public Income(Long id, Double amount, Instant date, Type type, Category category) {
        super();
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.category = category;
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
