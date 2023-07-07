package com.financial.financeapp.entities.dto;

import com.financial.financeapp.entities.enums.CategoryStatus;
import com.financial.financeapp.entities.enums.TypeStatus;
import com.financial.financeapp.entities.impl.Income;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class IncomeDTO {

    private Long id;
    private Double amount;
    private String date;
    private TypeDTO type;
    private CategoryDTO category;
    private String description;

    public IncomeDTO(Long id, Double amount, String date, TypeDTO type, CategoryDTO category, String description) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public IncomeDTO() {

    }

    public List<IncomeDTO> prepareData(List<Income> incomes) {
        List<IncomeDTO> incomesDTO = incomes.stream()
                .map(income -> new IncomeDTO(
                        income.getId(),
                        income.getAmount(),
                        income.getDate().toString(),
                        new TypeDTO(
                                income.getType().getId(),
                                TypeStatus.valueOf(income.getType().getType()).toString()
                        ),
                        new CategoryDTO(
                                income.getCategory().getId(),
                                CategoryStatus.valueOf(income.getCategory().getCategory()).toString()
                        ),
                        income.getDescription()
                ))
                .collect(Collectors.toList());
        return incomesDTO;
    }

    public Optional<IncomeDTO> prepareData(Optional<Income> income) {
        return Optional.of(new IncomeDTO(
                income.get().getId(),
                income.get().getAmount(),
                income.get().getDate().toString(),
                new TypeDTO(
                        income.get().getType().getId(),
                        TypeStatus.valueOf(income.get().getType().getType()).toString()
                ),
                new CategoryDTO(
                        income.get().getCategory().getId(),
                        CategoryStatus.valueOf(income.get().getCategory().getCategory()).toString()
                ),
                income.get().getDescription()
        ));
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomeDTO that = (IncomeDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "IncomeDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
