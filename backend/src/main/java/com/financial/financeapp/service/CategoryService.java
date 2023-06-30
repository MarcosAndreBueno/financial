package com.financial.financeapp.service;

import com.financial.financeapp.entities.enums.CategoryStatus;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<String> findAll() {
        List<Category> categories = categoryRepository.findAll();
        //converter enums para string
        List<String> categoryCodes =
                categories.stream()
                .map(category -> CategoryStatus.valueOf(category.getCategory()).toString())
                .collect(Collectors.toList());
        return categoryCodes;
    }

    public Category findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.get();
    }
}
