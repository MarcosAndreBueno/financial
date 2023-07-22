package com.financial.financeapp.resources;

import com.financial.financeapp.entities.dto.TypeDTO;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Type;
import com.financial.financeapp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void insert(@RequestBody Type type) {
        typeService.insert(type);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Type> update(@PathVariable Long id, @RequestBody Type type) {
        return typeService.update(id, type);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return typeService.deleteById(id);
    }
}
