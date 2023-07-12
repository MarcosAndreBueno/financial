package com.financial.financeapp.database;

import com.financial.financeapp.entities.Account;
import com.financial.financeapp.entities.enums.CategoryStatus;
import com.financial.financeapp.entities.enums.TypeStatus;
import com.financial.financeapp.entities.impl.Category;
import com.financial.financeapp.entities.impl.Income;
import com.financial.financeapp.entities.impl.Outcome;
import com.financial.financeapp.entities.impl.Type;
import com.financial.financeapp.repositories.*;
import org.hibernate.Hibernate;
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
        Type t1 = new Type(null, TypeStatus.INCOME_L);
        typeRepository.save(t1);

        Category c1 = new Category(null, CategoryStatus.SALARY);
        categoryRepository.save(c1);
        Category c2 = new Category(null, CategoryStatus.SIDE_GIGS);
        categoryRepository.save(c2);

        Account a1 = new Account(null, "Account One");
        Account a2 = new Account(null, "Account Two");
        accountRepository.save(a1);
        accountRepository.save(a2);

        Income i1 = new Income(null, 50.0, LocalDate.parse("2023-06-20"), a1, t1, c1, "Income 1");
        Income i2 = new Income(null, 70.0, LocalDate.parse("2023-06-20"), a1, t1, c1, "Income 2");
        Income i3 = new Income(null, 90.0, LocalDate.parse("2023-02-21"), a2, t1, c1, "Income 3");
        Income i4 = new Income(null, 20.0, LocalDate.parse("2023-02-24"), a2, t1, c2, "Income 4");
        Income i5 = new Income(null, 10.0, LocalDate.parse("2023-03-17"), a1, t1, c2, "Income 5");
        incomeRepository.saveAll(Arrays.asList(i1,i2,i3,i4,i5));

        t1.getIncomes().addAll(Arrays.asList(i1,i2));
        c1.getIncomes().addAll(Arrays.asList(i1,i2));

        Outcome o1 = new Outcome(null, 750.0, LocalDate.parse("2023-06-21"), a1, t1, c1, "Outcome 1");
        Outcome o2 = new Outcome(null, 600.0, LocalDate.parse("2023-03-24"), a1, t1, c1, "Outcome 2");
        Outcome o3 = new Outcome(null, 500.0, LocalDate.parse("2023-04-24"), a2, t1, c1, "Outcome 3");
        Outcome o4 = new Outcome(null, 800.0, LocalDate.parse("2023-06-24"), a2, t1, c1, "Outcome 4");
        Outcome o5 = new Outcome(null, 900.0, LocalDate.parse("2023-02-24"), a1, t1, c1, "Outcome 5");
        outcomeRepository.saveAll(Arrays.asList(o1,o2, o3, o4, o5));

        t1.getOutcomes().addAll(Arrays.asList(o1,o2));
        c1.getOutcomes().addAll(Arrays.asList(o1,o2));
    }
}