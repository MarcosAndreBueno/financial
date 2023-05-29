package com.financial.financeapp.database;

import com.financial.financeapp.entities.Income;
import com.financial.financeapp.entities.Outcome;
import com.financial.financeapp.entities.Type;
import com.financial.financeapp.repositories.IncomeRepository;
import com.financial.financeapp.repositories.OutcomeRepository;
import com.financial.financeapp.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
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
    
    @Override
    public void run(String... args) throws Exception {
        Type t1 = new Type(null, "Home");
        typeRepository.save(t1);

        Income i1 = new Income(null, 50.0, Instant.parse("2019-06-20T19:53:07Z"), t1);
        Income i2 = new Income(null, 70.0, Instant.parse("2019-06-20T20:00:42Z"), t1);

        incomeRepository.saveAll(Arrays.asList(i1,i2));

        Outcome o1 = new Outcome(null, 75.0, Instant.parse("2019-06-21T19:57:00Z"));
        Outcome o2 = new Outcome(null, 100.0, Instant.parse("2019-06-24T15:50:20Z"));
        outcomeRepository.saveAll(Arrays.asList(o1,o2));

    }
}
