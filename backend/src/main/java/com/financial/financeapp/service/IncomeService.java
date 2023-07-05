package com.financial.financeapp.service;

import com.financial.financeapp.entities.enums.CategoryStatus;
import com.financial.financeapp.entities.enums.TypeStatus;
import com.financial.financeapp.entities.dto.IncomeDTO;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Income;
import com.financial.financeapp.entities.impl.Type;
import com.financial.financeapp.repositories.CategoryRepository;
import com.financial.financeapp.repositories.IncomeRepository;
import com.financial.financeapp.repositories.TypeRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    IncomeRepository incomeRepository;

    public List<IncomeDTO> findAll() {
        List<Income> incomes = incomeRepository.findAll();
        return new IncomeDTO().prepareData(incomes);
    }

    public Optional<IncomeDTO> findById(Long id) {
        Optional<Income> income = incomeRepository.findById(id);
        return new IncomeDTO().prepareData(income);
    }

    //por enquanto, injetar dependências em service
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    CategoryRepository categoryRepository;
    public void insert(IncomeDTO incomeDTO) {
        TypeStatus typeStatus = TypeStatus.valueOf(incomeDTO.getType());
        Long tID = Long.valueOf(typeStatus.getCode());
        Type type = typeRepository.getReferenceById(tID);

        CategoryStatus categoryStatus = CategoryStatus.valueOf(incomeDTO.getCategory());
        Long cID = Long.valueOf(categoryStatus.getCode());
        Category category = categoryRepository.getReferenceById(cID);

        Income income = new Income(
                null,
                incomeDTO.getAmount(),
                LocalDate.parse(incomeDTO.getDate()),
                type,
                category,
                incomeDTO.getDescription()
        );
        incomeRepository.save(income);
    }

    public ResponseEntity<Income> update(Long id, IncomeDTO incomeDTO) {
        Optional<Income> incomeUpdate = incomeRepository.findById(id);

        //usar método find para evitar jackson lazyloading error
        TypeStatus typeStatus = TypeStatus.valueOf(incomeDTO.getType());
        Long tID = Long.valueOf(typeStatus.getCode());
        Type type = typeRepository.findById(tID).get();

        CategoryStatus categoryStatus = CategoryStatus.valueOf(incomeDTO.getCategory());
        Long cID = Long.valueOf(categoryStatus.getCode());
        Category category = categoryRepository.findById(cID).get();

        return incomeUpdate
                .map(item -> {
                    item.setAmount(incomeDTO.getAmount());
                    item.setDate(LocalDate.parse(incomeDTO.getDate()));
                    item.setType(type);
                    item.setCategory(category);
                    item.setDescription(incomeDTO.getDescription());
                    Income update = incomeRepository.save(item);
                    return ResponseEntity.ok().body(update);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
