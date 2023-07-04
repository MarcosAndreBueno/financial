package com.financial.financeapp.entities.dto;

import com.financial.financeapp.entities.enums.CategoryStatus;
import com.financial.financeapp.entities.impl.Category;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryDTO {

    private Long id;
    private String category;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String category) {
        this.id = id;
        this.category = category;
    }

    public List<CategoryDTO> prepareCategoriesData(List<Category> categories) {
        //converter enums para string
        return categories.stream()
        .map(category -> new CategoryDTO(
                category.getId(),
                CategoryStatus.valueOf(category.getCategory()).toString()))
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> prepareCategoriesData(Optional<Category> categories) {
        return categories.map(category -> new CategoryDTO(
                category.getId(),
                CategoryStatus.valueOf(category.getCategory()).toString()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }
}
