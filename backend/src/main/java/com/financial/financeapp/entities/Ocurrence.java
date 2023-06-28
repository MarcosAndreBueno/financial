package com.financial.financeapp.entities;

import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Type;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@MappedSuperclass
public abstract class Ocurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Double amount;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    protected LocalDate date;

    @ManyToOne
    @JoinColumn(name = "type_id")
    protected Type type;

    @ManyToOne
    @JoinColumn(name = "category_id")
    protected Category category;


    protected String description;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
