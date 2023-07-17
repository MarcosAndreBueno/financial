package com.financial.financeapp.entities.dto.impl;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.entities.dto.CategoryDTO;
import com.financial.financeapp.entities.dto.OccurrenceDTO;
import com.financial.financeapp.entities.dto.TypeDTO;
import com.financial.financeapp.entities.enums.CategoryStatus;
import com.financial.financeapp.entities.enums.TypeStatus;
import com.financial.financeapp.entities.impl.Outcome;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class OutcomeDTO extends OccurrenceDTO {

    public OutcomeDTO(Long id, Double amount, String date, Account account, TypeDTO type, CategoryDTO category, String description) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.account = account;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public OutcomeDTO() {

    }

    public List<OutcomeDTO> prepareData(List<Outcome> outcomes) {
        List<OutcomeDTO> outcomesDTO = outcomes.stream()
                .map(outcome -> new OutcomeDTO(
                        outcome.getId(),
                        outcome.getAmount(),
                        outcome.getDate().toString(),
                        outcome.getAccount(),
                        new TypeDTO(
                                outcome.getType().getId(),
                                TypeStatus.valueOf(outcome.getType().getType()).toString()
                        ),
                        new CategoryDTO(
                                outcome.getCategory().getId(),
                                CategoryStatus.valueOf(outcome.getCategory().getCategory()).toString()
                        ),
                        outcome.getDescription()
                ))
                .collect(Collectors.toList());
        return outcomesDTO;
    }

    public Optional<OutcomeDTO> prepareData(Optional<Outcome> outcome) {
        return Optional.of(new OutcomeDTO(
                outcome.get().getId(),
                outcome.get().getAmount(),
                outcome.get().getDate().toString(),
                outcome.get().getAccount(),
                new TypeDTO(
                        outcome.get().getType().getId(),
                        TypeStatus.valueOf(outcome.get().getType().getType()).toString()
                ),
                new CategoryDTO(
                        outcome.get().getCategory().getId(),
                        CategoryStatus.valueOf(outcome.get().getCategory().getCategory()).toString()
                ),
                outcome.get().getDescription()
        ));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutcomeDTO that = (OutcomeDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
