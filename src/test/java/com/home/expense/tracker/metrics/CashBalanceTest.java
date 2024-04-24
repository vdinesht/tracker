package com.home.expense.tracker.metrics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CashBalanceTest {
    @Autowired
    CashBalance cashBalance;
    @Test
    void testBalance(){
        assertTrue(cashBalance.getBalance(LocalDate.of(2021,1,1), LocalDate.of(2024,1,31)) > 0);
    }
}
