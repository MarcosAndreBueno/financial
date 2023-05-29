package com.financial.financeapp.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

import java.time.Instant;

@MappedSuperclass
public abstract class Ocurrence {
    protected Double amount;
    protected Instant date;

    @ManyToOne
    @JoinColumn(name = "type_id")
    protected Type type;

    @ManyToOne
    @JoinColumn(name = "category_id")
    protected Category category;
}
