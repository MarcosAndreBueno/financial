package com.financial.financeapp.database;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.entities.enums.CategoryStatus;
import com.financial.financeapp.entities.enums.TypeStatus;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Income;
import com.financial.financeapp.entities.impl.Outcome;
import com.financial.financeapp.entities.impl.Type;
import com.financial.financeapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class Test implements CommandLineRunner {

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AccountRepository accountRepository;
    
    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 15; i++) {
            Type t1 = new Type(null, TypeStatus.valueOf(i).toString());
            typeRepository.save(t1);
        }

        for (int i = 1; i <= 43; i++) {
            Category c1 = new Category(null, CategoryStatus.valueOf(i).toString());
            categoryRepository.save(c1);
        }

        Account a1 = new Account(null, "Account One");
        Account a2 = new Account(null, "Account Two");
        Account a3 = new Account(null, "Account Three");
        accountRepository.saveAll(Arrays.asList(a1,a2,a3));

        //Incomes
        Income i1 = new Income(
                null, 10000.0, LocalDate.parse("2023-07-07"), a1,
                typeRepository.getReferenceById(1L), categoryRepository.getReferenceById(1L),
                "Example One" );
        Income i2 = new Income(
                null, 1000.0, LocalDate.parse("2023-07-07"), a2,
                typeRepository.getReferenceById(1L), categoryRepository.getReferenceById(2L),
                "Example Two" );
        Income i3 = new Income(
                null, 250.0, LocalDate.parse("2023-08-07"), a2,
                typeRepository.getReferenceById(2L), categoryRepository.getReferenceById(3L),
                "Example Three" );
        Income i4 = new Income(
                null, 5000.0, LocalDate.parse("2023-08-07"), a3,
                typeRepository.getReferenceById(3L), categoryRepository.getReferenceById(4L),
                "Example Four" );

        incomeRepository.saveAll(Arrays.asList(i1,i2,i3,i4));

        //Outcomes
        Outcome o1 = new Outcome(
                null, 4000.0, LocalDate.parse("2023-07-07"), a1,
                typeRepository.getReferenceById(4L), categoryRepository.getReferenceById(6L),
                "Example One" );
        Outcome o2 = new Outcome(
                null, 100.0, LocalDate.parse("2023-07-07"), a2,
                typeRepository.getReferenceById(5L), categoryRepository.getReferenceById(9L),
                "Example Two" );
        Outcome o3 = new Outcome(
                null, 500.0, LocalDate.parse("2023-08-07"), a2,
                typeRepository.getReferenceById(6L), categoryRepository.getReferenceById(14L),
                "Example Three" );
        Outcome o4 = new Outcome(
                null, 1200.0, LocalDate.parse("2023-08-07"), a3,
                typeRepository.getReferenceById(7L), categoryRepository.getReferenceById(19L),
                "Example Four" );

        outcomeRepository.saveAll(Arrays.asList(o1,o2,o3,o4));
    }
}