package com.financial.financeapp.resources;

import com.financial.financeapp.entities.dao.IncomeDao;
import com.financial.financeapp.entities.impl.Income;
import com.financial.financeapp.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        //Optional<Income> para Optional<ResponseEntity>
        return incomeService.findById(id)
                .map(item -> ResponseEntity.ok().body(item))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void insert(@RequestBody IncomeDao incomeHandle) {
        incomeService.insert(incomeHandle);
    }
}
