package com.financial.financeapp.resources;

import com.financial.financeapp.entities.dto.impl.IncomeDTO;
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
    public ResponseEntity<List<IncomeDTO>> findAll() {
        List<IncomeDTO> i = incomeService.findAll();
        return ResponseEntity.ok().body(i);
    }

    @GetMapping(value = "/{month}/{year}")
    public ResponseEntity<List<IncomeDTO>> findAllByMonthAndYear(@PathVariable int month, @PathVariable int year) {
        List<IncomeDTO> i = incomeService.findAllByMonthAndYear(month, year);
        return ResponseEntity.ok().body(i);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<IncomeDTO> findById(@PathVariable Long id) {
        //Optional<Income> para Optional<ResponseEntity>
        return incomeService.findById(id)
                .map(item -> ResponseEntity.ok().body(item))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/account/{id}")
    public @ResponseBody Double findIncomeByAccount(@PathVariable Long id) {
        return incomeService.findIncomeByAccount(id);
    }

    @PostMapping
    public void insert(@RequestBody IncomeDTO incomeDTO) {
        incomeService.insert(incomeDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Income> update(@PathVariable Long id, @RequestBody IncomeDTO incomeDTO) {
        return incomeService.update(id, incomeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return incomeService.findById(id)
                .map( item -> {
                    incomeService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
