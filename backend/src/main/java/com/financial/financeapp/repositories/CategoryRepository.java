package com.financial.financeapp.repositories;

import com.financial.financeapp.entities.impl.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
