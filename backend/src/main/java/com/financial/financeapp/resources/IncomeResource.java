package com.financial.financeapp.resources;

import com.financial.financeapp.entities.impl.Income;
import com.financial.financeapp.repositories.IncomeRepository;
import com.financial.financeapp.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/api/income")
public class IncomeResource {

    @Autowired
    IncomeService incomeService;

    @GetMapping
    public ResponseEntity<List<Income>> findAll() {
        List<Income> i = incomeService.findAll();
        return ResponseEntity.ok().body(i);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Income> findById(@PathVariable Long id) {
        Income i = incomeService.findById(id);
        return ResponseEntity.ok().body(i);
    }

}
