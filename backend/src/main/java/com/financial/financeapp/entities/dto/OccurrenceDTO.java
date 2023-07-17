package com.financial.financeapp.entities.dto;

import com.financial.financeapp.entities.Account;

public abstract class OccurrenceDTO {
    
    protected Long id;
    protected Double amount;
    protected String date;
    protected Account account;
    protected TypeDTO type;
    protected CategoryDTO category;
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

    public TypeDTO getType() {
        return type;
    }

    public void setType(TypeDTO type) {
        this.type = type;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
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
