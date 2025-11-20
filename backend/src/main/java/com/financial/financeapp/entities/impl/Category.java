package com.financial.financeapp.entities.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_category")
@SQLDelete(sql = "UPDATE tb_category SET status_active = false WHERE id = ?")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double expectedAmount;

    private boolean isIncome;

    private Boolean status_active = true;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Income> incomes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Outcome> outcomes = new HashSet<>();

    public Category(Long id, String name, boolean isIncome, Double expectedAmount) {
        this.id = id;
        this.name = name;
        this.isIncome = isIncome;
        this.expectedAmount = expectedAmount;
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

    public Double getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(Double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIsIncome(boolean isIncome) {
        this.isIncome = isIncome;
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

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", expectedAmount=" + expectedAmount + ", isIncome=" + isIncome
                + ", status_active=" + status_active;
    }
}