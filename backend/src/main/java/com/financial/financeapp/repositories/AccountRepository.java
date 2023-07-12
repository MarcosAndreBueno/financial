package com.financial.financeapp.repositories;

import com.financial.financeapp.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE (a.name) = :name")
    Optional<Account> findByName(String name);

}
