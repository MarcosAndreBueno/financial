package com.financial.financeapp.service;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.entities.dto.impl.IncomeDTO;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Income;
import com.financial.financeapp.entities.impl.Type;

import com.financial.financeapp.repositories.IncomeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    AccountService accountService;

    @Autowired
    TypeService typeService;

    public List<IncomeDTO> findAll() {
        List<Income> incomes = incomeRepository.findAll();
        return new IncomeDTO().prepareData(incomes);
    }

    public List<IncomeDTO> findAllByMonthAndYear(int month, int year) {
        List<Income> incomes = incomeRepository.findByMonthAndYear(month,year);
        return new IncomeDTO().prepareData(incomes);
    }

    public Optional<IncomeDTO> findById(Long id) {
        Optional<Income> income = incomeRepository.findById(id);
        return new IncomeDTO().prepareData(income);
    }

    public Double findIncomeByAccount(Long id) {
        List<IncomeDTO> incomes = this.findAll();
        double total = incomes.stream()
                .filter(income -> income.getAccount().getId().equals(id))
                .mapToDouble(IncomeDTO::getAmount)
                .sum();
        return total;
    }

    @Transactional
    public void insert(IncomeDTO incomeDTO) {
        //lazy proxy initialization
        Type type = typeService.getProxyInstanceById(incomeDTO);
        Category category = categoryService.getProxyInstanceById(incomeDTO);
        Account account = accountService.getProxyInstanceById(incomeDTO);

        Income income = new Income(
                null,
                incomeDTO.getAmount(),
                LocalDate.parse(incomeDTO.getDate()),
                account,
                type,
                category,
                incomeDTO.getDescription()
        );
        incomeRepository.save(income);

        Double addValue = income.getAmount();
        accountService.updateAmount(account.getId(), addValue);
    }

    @Transactional
    public ResponseEntity<Income> update(Long id, IncomeDTO incomeDTO) {
        Optional<Income> incomeUpdate = incomeRepository.findById(id);

        //usar método find para evitar LazyInitializationException
        Type type = typeService.getEntityInstanceById(incomeDTO);
        Category category = categoryService.getEntityInstanceById(incomeDTO);
        Account prevAccount = accountService.findById(incomeUpdate.get().getAccount().getId()).orElse(null);
        Account actAccount = accountService.getEntityInstanceById(incomeDTO);

        //atualizar valor em contas (reseta valor antigo e adiciona novo)
        Double resetValue = incomeUpdate.get().getAmount() * -1;
        Double newValue = incomeDTO.getAmount();
        accountService.updateAmount(prevAccount.getId(), resetValue);
        accountService.updateAmount(actAccount.getId(), newValue);

        return incomeUpdate
                .map(item -> {
                    item.setAmount(incomeDTO.getAmount());
                    item.setDate(LocalDate.parse(incomeDTO.getDate()));
                    item.setAccount(actAccount);
                    item.setType(type);
                    item.setCategory(category);
                    item.setDescription(incomeDTO.getDescription());
                    Income update = incomeRepository.save(item);
                    return ResponseEntity.ok().body(update);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Income> income = incomeRepository.findById(id);
        Account account = income.get().getAccount();

        Double subtractValue = income.get().getAmount() * -1;
        accountService.updateAmount(account.getId(), subtractValue);

        incomeRepository.deleteById(id);
    }
}
