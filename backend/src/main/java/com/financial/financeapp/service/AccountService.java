package com.financial.financeapp.service;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.entities.dto.OccurrenceDTO;
import com.financial.financeapp.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;


    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account;
    }

    public Optional<Account> findByName(String name) {
        Optional<Account> account = accountRepository.findByName(name);
        return account;
    }

    public void insert(Account account) {
        if (accountRepository.findByName(account.getName()).isEmpty())
            accountRepository.save(account);
    }

    public ResponseEntity<Account> update(Long id, Account account) {
        Optional<Account> accountUpdate = accountRepository.findById(id);
        return accountUpdate
                .map(item -> {
                    item.setName(account.getName());
                    Account update = accountRepository.save(item);
                    return ResponseEntity.ok().body(update);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //soft delete
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    //lazyloading
    public Account getProxyInstanceById(OccurrenceDTO occurrenceDTO) {
        Long id = findByName(occurrenceDTO.getAccount().getName()).get().getId();
        return accountRepository.getReferenceById(id);
    }

    //entitidade totalmente carregada
    public Account getEntityInstanceById(OccurrenceDTO occurrenceDTO) {
        Long id = findByName(occurrenceDTO.getAccount().getName()).get().getId();
        return accountRepository.findById(id).get();
    }
}
