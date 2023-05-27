package com.financial.financeapp.resources;

import com.financial.financeapp.entities.Outcome;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping(value = "/outcome")
public class OutcomeResource {

    @GetMapping
    public ResponseEntity<Outcome> findAll() {
        Outcome i = new Outcome(1L, 75.0, Instant.parse("2019-06-21T19:57:07Z"));
        return ResponseEntity.ok().body(i);
    }
}
