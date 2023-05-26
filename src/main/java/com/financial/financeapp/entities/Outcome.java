package com.financial.financeapp.entities;

import java.io.Serializable;
import java.time.Instant;

public class Outcome implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double value;
    private Instant date;

    public Outcome(Long id, Double value, Instant date) {
        this.id = id;
        this.value = value;
        this.date = date;
    }

    public Outcome() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Outcome outcome = (Outcome) o;

        if (!id.equals(outcome.id)) return false;
        if (!value.equals(outcome.value)) return false;
        return date.equals(outcome.date);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
