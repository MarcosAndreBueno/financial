package com.financial.financeapp.service;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.entities.dto.impl.IncomeDTO;
import com.financial.financeapp.entities.dto.impl.OutcomeDTO;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Outcome;
import com.financial.financeapp.entities.impl.Type;

import com.financial.financeapp.repositories.OutcomeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OutcomeService {

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TypeService typeService;

    @Autowired
    AccountService accountService;

    public List<OutcomeDTO> findAll() {
        List<Outcome> outcomes = outcomeRepository.findAll();
        return new OutcomeDTO().prepareData(outcomes);
    }

    public List<OutcomeDTO> findAllByMonthAndYear(int month, int year) {
        List<Outcome> outcomes = outcomeRepository.findByMonthAndYear(month,year);
        return new OutcomeDTO().prepareData(outcomes);
    }

    public Optional<OutcomeDTO> findById(Long id) {
        Optional<Outcome> outcome = outcomeRepository.findById(id);
        return new OutcomeDTO().prepareData(outcome);
    }

    public Double findOutcomeByAccount(Long id) {
        List<OutcomeDTO> outcomes = this.findAll();
        double total = outcomes.stream()
                .filter(outcome -> outcome.getAccount().getId().equals(id))
                .mapToDouble(OutcomeDTO::getAmount)
                .sum();
        return total;
    }

    public void insert(OutcomeDTO outcomeDTO) {
        //lazy proxy initialization
        Type type = typeService.getProxyInstanceById(outcomeDTO);
        Category category = categoryService.getProxyInstanceById(outcomeDTO);
        Account account = accountService.getProxyInstanceById(outcomeDTO);

        Outcome outcome = new Outcome(
                null,
                outcomeDTO.getAmount(),
                LocalDate.parse(outcomeDTO.getDate()),
                account,
                type,
                category,
                outcomeDTO.getDescription()
        );
        outcomeRepository.save(outcome);
    }

    public ResponseEntity<Outcome> update(Long id, OutcomeDTO outcomeDTO) {
        Optional<Outcome> outcomeUpdate = outcomeRepository.findById(id);

        //usar método find para evitar LazyInitializationException
        Type type = typeService.getEntityInstanceById(outcomeDTO);
        Category category = categoryService.getEntityInstanceById(outcomeDTO);
        Account account = accountService.getEntityInstanceById(outcomeDTO);

        return outcomeUpdate
                .map(item -> {
                    item.setAmount(outcomeDTO.getAmount());
                    item.setDate(LocalDate.parse(outcomeDTO.getDate()));
                    item.setAccount(account);
                    item.setType(type);
                    item.setCategory(category);
                    item.setDescription(outcomeDTO.getDescription());
                    Outcome update = outcomeRepository.save(item);
                    return ResponseEntity.ok().body(update);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public void deleteById(Long id) {
        outcomeRepository.deleteById(id);
    }
}
