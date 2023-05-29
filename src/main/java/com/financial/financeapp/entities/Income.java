package com.financial.financeapp.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_income")
public class Income implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Instant date;

    //    @JoinTable(name = "tb_income_type", joinColumns = @JoinColumn(name = "income_id"), inverseJoinColumns = @JoinColumn(type_id));
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    public Income(Long id, Double amount, Instant date, Type type) {
        super();
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public Income() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
