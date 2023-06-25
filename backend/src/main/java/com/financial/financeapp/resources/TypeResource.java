package com.financial.financeapp.resources;

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
    public ResponseEntity<List<Type>> findAll() {
        List<Type> t = typeService.findAll();
        return ResponseEntity.ok().body(t);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Type> findById(@PathVariable Long id) {
        Type t = typeService.findById(id);
        return ResponseEntity.ok().body(t);
    }
}
