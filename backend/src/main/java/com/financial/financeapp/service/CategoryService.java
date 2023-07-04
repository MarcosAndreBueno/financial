package com.financial.financeapp.service;

import com.financial.financeapp.entities.dto.CategoryDTO;
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

    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return new CategoryDTO().prepareCategoriesData(categories);
    }

    public CategoryDTO findById(Long id) {
        Optional<Category> cat = categoryRepository.findById(id);
        Optional<CategoryDTO> category = new CategoryDTO().prepareCategoriesData(cat);
        return category.get();
    }
}
