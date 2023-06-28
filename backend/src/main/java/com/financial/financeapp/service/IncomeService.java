package com.financial.financeapp.service;

import com.financial.financeapp.entities.handler.IncomeHandle;
import com.financial.financeapp.entities.impl.Income;
import com.financial.financeapp.repositories.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    IncomeRepository incomeRepository;

    public List<Income> findAll() {
        return incomeRepository.findAll();
    }

    public Income findById(Long id) {
        Optional<Income> income = incomeRepository.findById(id);
        return income.get();
    }

    public void insert(IncomeHandle incomeHandle) {
        //por enquanto, usar dados null para evitar o erro TransientPropertyValueException
        Income income = new Income(
                null,
                incomeHandle.getAmount(),
                LocalDate.parse(incomeHandle.getDate()),
                null,
                null,
                incomeHandle.getDescription()
        );
        incomeRepository.save(income);
    }
}
