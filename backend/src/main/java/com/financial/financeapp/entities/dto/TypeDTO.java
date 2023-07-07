package com.financial.financeapp.entities.dto;

import com.financial.financeapp.entities.enums.TypeStatus;
import com.financial.financeapp.entities.impl.Type;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class TypeDTO {

    private Long id;
    private String type;

    public TypeDTO() {
    }

    public TypeDTO(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public List<TypeDTO> prepareTypesData(List<Type> types) {
        //converter enums para string
        return types.stream()
        .map(type -> new TypeDTO(
                type.getId(),
                TypeStatus.valueOf(type.getType()).toString()))
                .collect(Collectors.toList());
    }

    public Optional<TypeDTO> prepareTypesData(Optional<Type> types) {
        return types.map(type -> new TypeDTO(
                type.getId(),
                TypeStatus.valueOf(type.getType()).toString()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDTO that = (TypeDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
