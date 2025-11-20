package com.financial.financeapp.entities.dto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.financial.financeapp.entities.impl.Type;

public class TypeDTO {

    private Long id;
    private String name;
    private Boolean isIncome;
    private Boolean status_active = true;

    public TypeDTO() {
    }

    public TypeDTO(Long id, String name, Boolean isIncome, Boolean status_active) {
        this.id = id;
        this.name = name;
        this.isIncome = isIncome;
        this.status_active = status_active;
    }

    public List<TypeDTO> prepareTypesData(List<Type> types) {
        //converter enums para string
        return types.stream()
                .map(type -> new TypeDTO(
                type.getId(),
                type.getName(),
                type.isIncome(),
                type.getStatus_active()))
                .collect(Collectors.toList());
    }

    public Optional<TypeDTO> prepareTypesData(Optional<Type> types) {
        return types.map(type -> new TypeDTO(
                type.getId(),
                type.getName(),
                type.isIncome(),
                type.getStatus_active()));
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

    public Boolean isIncome() {
        return isIncome;
    }

    public void setIsIncome(Boolean isIncome) {
        this.isIncome = isIncome;
    }

    public Boolean getStatus_active() {
        return status_active;
    }

    public void setStatus_active(Boolean status_active) {
        this.status_active = status_active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypeDTO that = (TypeDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "TypeDTO [id=" + id + ", name=" + name + ", isIncome=" + isIncome + ", status_active=" + status_active
                + "]";
    }
}
