package com.financial.financeapp.entities.dto;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Type;

public abstract class OccurrenceDTO {
    
    protected Long id;
    protected Double amount;
    protected String date;
    protected Account account;
    protected Type type;
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

    public void setAmount(Double value) {
        this.amount = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    @Override
    public String toString() {
        return "OccurrenceDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", account=" + account +
                ", type=" + type +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }
}
