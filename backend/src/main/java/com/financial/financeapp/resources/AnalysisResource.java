package com.financial.financeapp.resources;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.financeapp.service.AnalysisService;

@RestController
@RequestMapping(value = "/api/analysis")
public class AnalysisResource {

    @Autowired
    AnalysisService analysisService;

    @GetMapping(value = "/getCategoryExpenseIncreaseLastMonth")
    public ResponseEntity<Map<String, Double>> getCategoryExpenseIncreaseLastMonth() {
        Map<String, Double> result = analysisService.getCategoryExpenseIncreaseLastMonth();
        return ResponseEntity.ok().body(result);
    }
}