package com.financial.financeapp.service;

import com.financial.financeapp.entities.dto.CategoryDTO;
import com.financial.financeapp.entities.dto.OccurrenceDTO;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public void insert(Category category) {
        if (categoryRepository.findByName(category.getName()).isEmpty())
            categoryRepository.save(category);
    }

    public ResponseEntity<Category> update(Long id, Category category) {
        Optional<Category> categoryUpdate = categoryRepository.findById(id);
        return categoryUpdate
                .map(item -> {
                    item.setName(category.getName());
                    Category update = categoryRepository.save(item);
                    return ResponseEntity.ok().body(update);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //soft delete
    public ResponseEntity<Void> deleteById(Long id) {
        return categoryRepository.findById(id)
                .map( item -> {
                    categoryRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //lazyloading
    public Category getProxyInstanceById(OccurrenceDTO occurrenceDTO) {
        Long cID = occurrenceDTO.getCategory().getId();
        return categoryRepository.getReferenceById(cID);
    }

    //entitidade totalmente carregada
    public Category getEntityInstanceById(OccurrenceDTO occurrenceDTO) {
        Long cID = occurrenceDTO.getCategory().getId();
        return categoryRepository.findById(cID).get();
    }
}
