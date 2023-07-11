package com.financial.financeapp.service;

import com.financial.financeapp.entities.dto.CategoryDTO;
import com.financial.financeapp.entities.dto.OccurrenceDTO;
import com.financial.financeapp.entities.enums.CategoryStatus;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //lazyloading
    public Category getProxyInstanceById(OccurrenceDTO occurrenceDTO) {
        CategoryStatus categoryStatus = CategoryStatus.valueOf(occurrenceDTO.getCategory().getCategory());
        Long cID = Long.valueOf(categoryStatus.getCode());
        return categoryRepository.getReferenceById(cID);
    }

    //entitidade totalmente carregada
    public Category getEntityInstanceById(OccurrenceDTO occurrenceDTO) {
        CategoryStatus categoryStatus = CategoryStatus.valueOf(occurrenceDTO.getCategory().getCategory());
        Long cID = Long.valueOf(categoryStatus.getCode());
        return categoryRepository.findById(cID).get();
    }
}
