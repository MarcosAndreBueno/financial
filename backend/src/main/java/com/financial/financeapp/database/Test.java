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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Random;

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

        // acount
        Account a1 = new Account(null, "Account One");
        Account a2 = new Account(null, "Account Two");
        Account a3 = new Account(null, "Account Three");
        accountRepository.saveAll(Arrays.asList(a1,a2,a3));

        Random random = new Random();

        //Incomes
        for (int i = 0; i < 500; i++) {
            // random date
            int year = 2024 + random.nextInt(2); // 2024 ou 2025
            int month = 1 + random.nextInt(12);
            int maxDay = YearMonth.of(year, month).lengthOfMonth();
            int day = 1 + random.nextInt(maxDay);
            LocalDate date = LocalDate.of(year, month, day);

            // Tipo entre 1 e 3
            int typeId = 1 + random.nextInt(3);

            // Categoria depende do tipo
            int categoryId = 1 + random.nextInt(5);

            // Entre a1, a2 ou a3
            Account account;
            int accNum = 1 + random.nextInt(3);
            if (accNum == 1) account = a1;
            else if (accNum == 2) account = a2;
            else account = a3;

            // random value
            double value = 100 + random.nextDouble() * 300;
            double rounded = new BigDecimal(value)
                    .setScale(0, RoundingMode.HALF_UP)
                    .doubleValue();

            Income income = new Income(
                    null,
                    rounded,
                    date,
                    account,
                    typeRepository.getReferenceById((long) typeId),
                    categoryRepository.getReferenceById((long) categoryId),
                    "Random income " + (i + 1)
            );

            incomeRepository.save(income);
        }

        //Outcomes
        for (int i = 0; i < 500; i++) {
            //random date
            int year = 2024 + random.nextInt(2); // 2024 ou 2025
            int month = 1 + random.nextInt(12);
            int maxDay = YearMonth.of(year, month).lengthOfMonth();
            int day = 1 + random.nextInt(maxDay);
            LocalDate date = LocalDate.of(year, month, day);

            // Tipo entre 4 e 15
            int typeId = 4 + random.nextInt(12);

            // Categoria depende do tipo
            int categoryId = 0;
            switch (typeId) {
                case 4 -> categoryId = 6 + random.nextInt(3);      // 6 a 8
                case 5 -> categoryId = 9 + random.nextInt(5);      // 9 a 13
                case 6 -> categoryId = 14 + random.nextInt(5);     // 14 a 18
                case 7 -> categoryId = 19 + random.nextInt(4);     // 19 a 22
                case 8 -> categoryId = 23 + random.nextInt(4);     // 23 a 26
                case 9 -> categoryId = 27 + random.nextInt(2);     // 27 a 28
                case 10 -> categoryId = 29 + random.nextInt(5);    // 29 a 33
                case 11 -> categoryId = 34;                              // 34
                case 12 -> categoryId = 35 + random.nextInt(2);    // 35 a 36
                case 13 -> categoryId = 37 + random.nextInt(4);    // 37 a 40
                case 14 -> categoryId = 41 + random.nextInt(2);    // 41 a 42
                case 15 -> categoryId = 43;                              // 43
            }

            Account account = a1;

            //random value
            double value = 5 + random.nextDouble() * 100;
            double rounded = new BigDecimal(value)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            Outcome outcome = new Outcome(
                    null,
                    rounded,
                    date,
                    account,
                    typeRepository.getReferenceById((long) typeId),
                    categoryRepository.getReferenceById((long) categoryId),
                    "Random outcome " + (i + 1)
            );

            outcomeRepository.save(outcome);
        }
    }
}