package com.financial.financeapp.entities.dto.impl;

import com.financial.financeapp.entities.dto.CategoryDTO;
import com.financial.financeapp.entities.dto.OccurrenceDTO;
import com.financial.financeapp.entities.dto.TypeDTO;
import com.financial.financeapp.entities.enums.CategoryStatus;
import com.financial.financeapp.entities.enums.TypeStatus;
import com.financial.financeapp.entities.impl.Income;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class IncomeDTO extends OccurrenceDTO {

    public IncomeDTO(Long id, Double amount, String date, String account, TypeDTO type, CategoryDTO category, String description) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.account = account;
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
                        income.getAccount().getName(),
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
                income.get().getAccount().getName(),
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
}
