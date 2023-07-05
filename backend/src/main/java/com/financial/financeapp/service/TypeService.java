package com.financial.financeapp.service;

import com.financial.financeapp.entities.dto.IncomeDTO;
import com.financial.financeapp.entities.enums.TypeStatus;
import com.financial.financeapp.entities.impl.Type;
import com.financial.financeapp.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    public Type findById(Long id) {
        Optional<Type> type = typeRepository.findById(id);
        return type.get();
    }

    public Type getProxyInstanceById(IncomeDTO incomeDTO) {
        TypeStatus typeStatus = TypeStatus.valueOf(incomeDTO.getType());
        Long tID = Long.valueOf(typeStatus.getCode());
        return typeRepository.getReferenceById(tID);
    }

    public Type getEntityInstanceById(IncomeDTO incomeDTO) {
        TypeStatus typeStatus = TypeStatus.valueOf(incomeDTO.getType());
        Long tID = Long.valueOf(typeStatus.getCode());
        return typeRepository.findById(tID).get();
    }
}
