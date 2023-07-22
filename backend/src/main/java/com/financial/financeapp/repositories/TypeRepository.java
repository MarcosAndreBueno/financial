package com.financial.financeapp.repositories;

import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query("SELECT t FROM Type t WHERE (t.name) = :name")
    Optional<Category> findByName(String name);
}
