package com.financial.financeapp.entities.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.financial.financeapp.entities.enums.TypeStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_type")
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer type;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private Set<Income> incomes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private Set<Outcome> outcomes = new HashSet<>();

    public Type(Long id, TypeStatus typeStatus) {
        this.id = id;
        setTypeStatus(typeStatus);
    }

    private void setTypeStatus(TypeStatus typeStatus) {
        if (typeStatus != null) {
            this.type = typeStatus.getCode();
        }
    }

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }

    public Set<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(Set<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type1 = (Type) o;
        return id.equals(type1.id) && type.equals(type1.type) && incomes.equals(type1.incomes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, incomes);
    }
}
