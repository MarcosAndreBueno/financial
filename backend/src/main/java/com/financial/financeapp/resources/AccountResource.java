package com.financial.financeapp.resources;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/account")
public class AccountResource {

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Account> findAllByName(@PathVariable String name) {
        return accountService.findByName(name)
                .map(item -> ResponseEntity.ok().body(item))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void insert(@RequestBody Account account) {
        accountService.insert(account);
    }

    @PutMapping(value = "/{name}")
    public ResponseEntity<Account> update(@PathVariable String name, @RequestBody Account account) {
        return accountService.update(name, account);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteById(@PathVariable String name) {
        return accountService.findByName(name)
                .map( item -> {
                    accountService.deleteById(name);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
