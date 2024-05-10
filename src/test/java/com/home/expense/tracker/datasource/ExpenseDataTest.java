package com.home.expense.tracker.datasource;

import com.home.expense.tracker.core.PrimaryAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ExpenseDataTest {

    @Autowired
    ExpenseData expenseData;
    @Test
    void testCreditRows(){
        LocalDate from = LocalDate.of(2021,1,1);
        LocalDate to = LocalDate.of(2024,5,31);
        System.out.println("Cash Expenses since 1 Jan 2021 : " + expenseData.getCredit(from,to, PrimaryAccount.CashAsset));
        System.out.println("Cash Income since 1 Jan 2021 : " + expenseData.getDebit(from,to,PrimaryAccount.CashAsset));

        assertTrue(expenseData.getCredit(from,to,PrimaryAccount.CashAsset) - expenseData.getCreditRows(from,to,PrimaryAccount.CashAsset).stream().mapToDouble(DataRow2024::amount).sum() == 0);
        assertTrue(expenseData.getDebit(from,to,PrimaryAccount.CashAsset) - expenseData.getDebitRows(from,to,PrimaryAccount.CashAsset).stream().mapToDouble(DataRow2024::amount).sum() == 0);

    }

    @Test
    void testDebitRows(){

    }
}
