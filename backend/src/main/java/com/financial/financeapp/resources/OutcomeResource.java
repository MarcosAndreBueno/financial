package com.financial.financeapp.resources;

import com.financial.financeapp.entities.impl.Outcome;
import com.financial.financeapp.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/api/outcome")
public class OutcomeResource {

    @Autowired
    OutcomeService outcomeService;

    @GetMapping
    public ResponseEntity<List<Outcome>> findAll() {
        List<Outcome> o = outcomeService.findAll();
        return ResponseEntity.ok().body(o);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Outcome> findById(@PathVariable Long id) {
        Outcome o = outcomeService.findById(id);
        return ResponseEntity.ok().body(o);
    }
}
