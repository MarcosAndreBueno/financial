package com.financial.financeapp.resources;

import com.financial.financeapp.entities.dto.TypeDTO;
import com.financial.financeapp.entities.impl.Type;
import com.financial.financeapp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/type")
public class TypeResource {

    @Autowired
    TypeService typeService;

    @GetMapping
    public ResponseEntity<List<TypeDTO>> findAll() {
        List<TypeDTO> type = typeService.findAll();
        return ResponseEntity.ok().body(type);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TypeDTO> findById(@PathVariable Long id) {
        TypeDTO type = typeService.findById(id);
        return ResponseEntity.ok().body(type);
    }
}
