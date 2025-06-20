package com.financial.financeapp.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Outcome;
import com.financial.financeapp.repositories.AnalysisRepository;

@Service
public class AnalysisService {

    @Autowired
    AnalysisRepository analysisRepository;

    @Autowired
    OutcomeService outcomeService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TypeService typeService;

    public Map<String, Double> getCategoryExpenseIncreaseLastMonth() {
        LocalDate hoje = LocalDate.now();

        List<Outcome> outcomeCurrMonth = this.analysisRepository.getOucomeByYearMonthDay(hoje.getYear(), hoje.getMonthValue(), hoje.getDayOfMonth());

        hoje = hoje.minusMonths(1);
        List<Outcome> outcomePrevMonth = this.analysisRepository.getOucomeByYearMonthDay(hoje.getYear(), hoje.getMonthValue(), hoje.getDayOfMonth());

        Map<Category, Double> totalByCategoryCurrMonth = outcomeCurrMonth.stream()
                .collect(Collectors.groupingBy(
                        Outcome::getCategory,
                        Collectors.summingDouble(o -> o.getAmount() != null ? o.getAmount() : 0.0)
                ));

        Map<Category, Double> totalByCategoryPrevMonth = outcomePrevMonth.stream()
                .collect(Collectors.groupingBy(
                        Outcome::getCategory,
                        Collectors.summingDouble(o -> o.getAmount() != null ? o.getAmount() : 0.0)
                ));

        Map<String, Double> categoriesExpenseIncrease = totalByCategoryCurrMonth.entrySet().stream()
                .filter(entry -> {
                    String categoryName = entry.getKey().getName();
                    Double currValue = entry.getValue();
                    Double prevValue = totalByCategoryPrevMonth.entrySet().stream()
                            .filter(e -> e.getKey().getName().equals(categoryName))
                            .map(Map.Entry::getValue)
                            .findFirst()
                            .orElse(0.0);
                    return currValue > prevValue;
                })
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        entry -> {
                            String categoryName = entry.getKey().getName();
                            Double currValue = entry.getValue();
                            Double prevValue = totalByCategoryPrevMonth.entrySet().stream()
                                    .filter(e -> e.getKey().getName().equals(categoryName))
                                    .map(Map.Entry::getValue)
                                    .findFirst()
                                    .orElse(0.0);
                            return BigDecimal.valueOf(currValue - prevValue)
                                    .setScale(2, RoundingMode.HALF_UP)
                                    .doubleValue();
                        }
                ));

        return categoriesExpenseIncrease;
    }
}
