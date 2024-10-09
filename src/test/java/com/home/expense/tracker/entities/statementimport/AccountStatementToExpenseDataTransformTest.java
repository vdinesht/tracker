package com.home.expense.tracker.entities.statementimport;

import com.home.expense.tracker.entities.transaction.TransformAccountStatementToExpenseData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountStatementToExpenseDataTransformTest {
    @Autowired
    AccountStatementFactory statementFactory;
    @Autowired
    TransformAccountStatementToExpenseData transformAccountStatementToExpenseData;

    private final String statementFile = "C:\\Temp\\ExpenseTracker\\BankStatement\\OpTransactionHistory1JanTo31Dec2023UTF8.csv";
    @Test
    void testImportingAllAvailableStatements(){
        AccountStatement ex = statementFactory.getStatement(statementFile);
        ex.getAllRows().forEach(System.out::println);
        assertTrue(ex.getAllRows().size() > 0);

    }

    @Test
    void testIciciBankStatementToTransacationRowConversion(){
        AccountStatement ex = statementFactory.getStatement(statementFile);
        transformAccountStatementToExpenseData.transform(ex).forEach(System.out::println);
        assertTrue(transformAccountStatementToExpenseData.transform(ex).size()>0);

    }
}
