package com.financial.financeapp.entities.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_type")
@SQLDelete(sql = "UPDATE tb_type SET status_active = false WHERE id = ?")
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean status_active = true;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private Set<Income> incomes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private Set<Outcome> outcomes = new HashSet<>();

    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Type() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getStatus_active() {
        return status_active;
    }

    public void setStatus_active(Boolean status_active) {
        this.status_active = status_active;
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
        return id.equals(type1.id) && name.equals(type1.name) && incomes.equals(type1.incomes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, incomes);
    }
}
