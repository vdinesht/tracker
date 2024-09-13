package com.home.expense.tracker.metrics;

import com.home.expense.tracker.statementimport.AccountStatementType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BankBalanceTest {
    @Autowired
    BankBalance bankBalance;

    @Test
    void testBalance(){
        System.out.println("Bank balance: " + bankBalance.getBalance(AccountStatementType.BankICICIThoraipakkamDinesh,LocalDate.of(2021,1,1), LocalDate.of(2024,1,31)));
        assertTrue(true);
    }
}
