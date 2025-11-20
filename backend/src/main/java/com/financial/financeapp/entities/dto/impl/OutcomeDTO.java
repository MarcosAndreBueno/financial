package com.financial.financeapp.entities.dto.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.entities.dto.OccurrenceDTO;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Outcome;
import com.financial.financeapp.entities.impl.Type;

public class OutcomeDTO extends OccurrenceDTO {

    public OutcomeDTO(Long id, Double amount, String date, Account account, Type type, Category category, String description) {
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
                outcome.getType(),
                outcome.getCategory(),
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
                outcome.get().getType(),
                outcome.get().getCategory(),
                outcome.get().getDescription()
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
        OutcomeDTO that = (OutcomeDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
