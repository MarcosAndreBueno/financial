package com.financial.financeapp.service;

import com.financial.financeapp.entities.dto.OccurrenceDTO;
import com.financial.financeapp.entities.dto.TypeDTO;
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

    public List<TypeDTO> findAll() {
        List<Type> types = typeRepository.findAll();
        return new TypeDTO().prepareTypesData(types);
    }

    public TypeDTO findById(Long id) {
        Optional<Type> cat = typeRepository.findById(id);
        Optional<TypeDTO> type = new TypeDTO().prepareTypesData(cat);
        return type.get();
    }

    //lazyloading
    public Type getProxyInstanceById(OccurrenceDTO occurrenceDTO) {
        TypeStatus typeStatus = TypeStatus.valueOf(occurrenceDTO.getType().getType());
        Long cID = Long.valueOf(typeStatus.getCode());
        return typeRepository.getReferenceById(cID);
    }

    //entitidade totalmente carregada
    public Type getEntityInstanceById(OccurrenceDTO occurrenceDTO) {
        TypeStatus typeStatus = TypeStatus.valueOf(occurrenceDTO.getType().getType());
        Long cID = Long.valueOf(typeStatus.getCode());
        return typeRepository.findById(cID).get();
    }
}
