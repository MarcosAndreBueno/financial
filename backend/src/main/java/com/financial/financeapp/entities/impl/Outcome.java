package com.financial.financeapp.entities.impl;

import com.financial.financeapp.entities.Ocurrence;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "tb_outcome")
public class Outcome extends Ocurrence implements Serializable {
    private static final long serialVersionUID = 1L;

    public Outcome(Long id, Double amount, LocalDate date, Type type, Category category, String description) {
        super();
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public Outcome() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Outcome outcome = (Outcome) o;

        if (!id.equals(outcome.id)) return false;
        if (!amount.equals(outcome.amount)) return false;
        return date.equals(outcome.date);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
