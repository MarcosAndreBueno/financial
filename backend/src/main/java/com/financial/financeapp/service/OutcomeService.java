package com.financial.financeapp.service;

import com.financial.financeapp.entities.impl.Outcome;
import com.financial.financeapp.repositories.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutcomeService {

    @Autowired
    OutcomeRepository outcomeRepository;

    public List<Outcome> findAll() {
        return outcomeRepository.findAll();
    }

    public Outcome findById(Long id) {
        Optional<Outcome> outcome = outcomeRepository.findById(id);
        return outcome.get();
    }
}
