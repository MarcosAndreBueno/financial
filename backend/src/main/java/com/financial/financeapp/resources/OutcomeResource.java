package com.financial.financeapp.resources;

import com.financial.financeapp.entities.dto.impl.OutcomeDTO;
import com.financial.financeapp.entities.impl.Outcome;
import com.financial.financeapp.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/outcome")
public class OutcomeResource {

    @Autowired
    OutcomeService outcomeService;

    @GetMapping
    public ResponseEntity<List<OutcomeDTO>> findAll() {
        List<OutcomeDTO> o = outcomeService.findAll();
        return ResponseEntity.ok().body(o);
    }

    @GetMapping(value = "/{month}/{year}")
    public ResponseEntity<List<OutcomeDTO>> findAllByMonthAndYear(@PathVariable int month, @PathVariable int year) {
        List<OutcomeDTO> o = outcomeService.findAllByMonthAndYear(month, year);
        return ResponseEntity.ok().body(o);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OutcomeDTO> findById(@PathVariable Long id) {
        //Optional<Outcome> para Optional<ResponseEntity>
        return outcomeService.findById(id)
                .map(item -> ResponseEntity.ok().body(item))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/account/{id}")
    public @ResponseBody Double findOutcomeByAccount(@PathVariable Long id) {
        return outcomeService.findOutcomeByAccount(id);
    }

    @PostMapping
    public void insert(@RequestBody OutcomeDTO outcomeDTO) {
        outcomeService.insert(outcomeDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Outcome> update(@PathVariable Long id, @RequestBody OutcomeDTO outcomeDTO) {
        return outcomeService.update(id, outcomeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return outcomeService.findById(id)
                .map( item -> {
                    outcomeService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
