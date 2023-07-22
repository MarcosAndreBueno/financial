package com.financial.financeapp.entities.dto;

import com.financial.financeapp.entities.impl.Category;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryDTO {

    private Long id;
    private String name;
    private Boolean status_active = true;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name, Boolean status_active) {
        this.id = id;
        this.name = name;
        this.status_active = status_active;
    }

    public List<CategoryDTO> prepareCategoriesData(List<Category> categories) {
        //converter enums para string
        return categories.stream()
        .map(category -> new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getStatus_active()))
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> prepareCategoriesData(Optional<Category> categories) {
        return categories.map(category -> new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getStatus_active()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus_active() {
        return status_active;
    }

    public void setStatus_active(Boolean status_active) {
        this.status_active = status_active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
