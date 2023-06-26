package com.financial.financeapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Type;
import jakarta.persistence.*;

import java.time.Instant;

@MappedSuperclass
public abstract class Ocurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    protected Long id;
    protected Double amount;
    @JsonProperty("_date")
    protected Instant date;

    @ManyToOne
    @JoinColumn(name = "type_id")
    protected Type type;

    @ManyToOne
    @JoinColumn(name = "category_id")
    protected Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
