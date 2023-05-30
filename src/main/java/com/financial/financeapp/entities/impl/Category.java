package com.financial.financeapp.entities.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.financial.financeapp.entities.enums.CategoryStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer category;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Income> incomes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Outcome> outcomes = new HashSet<>();

    public Category(Long id, CategoryStatus categoryStatus) {
        this.id = id;
        setCategoryStatus(categoryStatus);
    }

    private void setCategoryStatus(CategoryStatus categoryStatus) {
        if (categoryStatus != null) {
            this.category = categoryStatus.getCode();
        }
    }

    public Category() {
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
        Category category1 = (Category) o;
        return id.equals(category1.id) && category.equals(category1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }
}
