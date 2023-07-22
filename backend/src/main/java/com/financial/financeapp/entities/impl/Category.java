package com.financial.financeapp.entities.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_category")
@SQLDelete(sql = "UPDATE tb_category SET status_active = false WHERE id = ?")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean status_active = true;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Income> incomes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Outcome> outcomes = new HashSet<>();

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus_active() {
        return status_active;
    }

    public void setStatus_active(Boolean status_active) {
        this.status_active = status_active;
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
        Category category1 = (Category) o;
        return id.equals(category1.id) && name.equals(category1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
