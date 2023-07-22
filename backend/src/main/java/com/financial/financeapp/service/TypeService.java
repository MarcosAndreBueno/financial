package com.financial.financeapp.service;

import com.financial.financeapp.entities.dto.OccurrenceDTO;
import com.financial.financeapp.entities.dto.TypeDTO;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Type;
import com.financial.financeapp.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        Optional<Type> tp = typeRepository.findById(id);
        Optional<TypeDTO> type = new TypeDTO().prepareTypesData(tp);
        return type.get();
    }

    public void insert(Type type) {
        if (typeRepository.findByName(type.getName()).isEmpty())
            typeRepository.save(type);
    }

    public ResponseEntity<Type> update(Long id, Type type) {
        Optional<Type> typeUpdate = typeRepository.findById(id);
        return typeUpdate
                .map(item -> {
                    item.setName(type.getName());
                    Type update = typeRepository.save(item);
                    return ResponseEntity.ok().body(update);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //soft delete
    public ResponseEntity<Void> deleteById(Long id) {
        return typeRepository.findById(id)
                .map( item -> {
                    typeRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //lazyloading
    public Type getProxyInstanceById(OccurrenceDTO occurrenceDTO) {
        Long cID = occurrenceDTO.getType().getId();
        return typeRepository.getReferenceById(cID);
    }

    //entitidade totalmente carregada
    public Type getEntityInstanceById(OccurrenceDTO occurrenceDTO) {
        Long cID = occurrenceDTO.getType().getId();
        return typeRepository.findById(cID).get();
    }
}
