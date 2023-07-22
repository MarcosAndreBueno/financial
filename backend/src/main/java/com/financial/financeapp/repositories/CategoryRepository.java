package com.financial.financeapp.repositories;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.entities.impl.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE (c.name) = :name")
    Optional<Category> findByName(String name);
}
