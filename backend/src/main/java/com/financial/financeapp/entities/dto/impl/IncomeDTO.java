package com.financial.financeapp.entities.dto.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.entities.dto.OccurrenceDTO;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Income;
import com.financial.financeapp.entities.impl.Type;

public class IncomeDTO extends OccurrenceDTO {

    public IncomeDTO(Long id, Double amount, String date, Account account, Type type, Category category, String description) {
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
                income.getAccount(),
                income.getType(),
                income.getCategory(),
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
                income.get().getAccount(),
                income.get().getType(),
                income.get().getCategory(),
                income.get().getDescription()
        ));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IncomeDTO that = (IncomeDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
