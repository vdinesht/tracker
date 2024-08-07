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
    TransactionData expenseData;
    @Test
    void testCreditRows(){
        LocalDate from = LocalDate.of(2021,1,1);
        LocalDate to = LocalDate.of(2024,5,31);
        System.out.println("Cash Expenses since 1 Jan 2021 : " + expenseData.getCreditSum(from,to, PrimaryAccount.CashAsset));
        System.out.println("Cash Income since 1 Jan 2021 : " + expenseData.getDebitSum(from,to,PrimaryAccount.CashAsset));

        assertTrue(expenseData.getCreditSum(from,to,PrimaryAccount.CashAsset) - expenseData.getCreditRows(from,to,PrimaryAccount.CashAsset).stream().mapToDouble(TransactionDataRow::amount).sum() == 0);
        assertTrue(expenseData.getDebitSum(from,to,PrimaryAccount.CashAsset) - expenseData.getDebitRows(from,to,PrimaryAccount.CashAsset).stream().mapToDouble(TransactionDataRow::amount).sum() == 0);

    }

    @Test
    void testDebitRows(){

    }
}
