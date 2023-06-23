package com.financial.financeapp.repositories;

import com.financial.financeapp.entities.impl.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
