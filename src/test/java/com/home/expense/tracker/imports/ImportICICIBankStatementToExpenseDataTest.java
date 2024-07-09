package com.home.expense.tracker.imports;

import com.home.expense.tracker.datasource.TransactionData;
import com.home.expense.tracker.datatransform.TransformAccountStatementToExpenseData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ImportICICIBankStatementToExpenseDataTest {
    @Autowired
    AccountStatementFactory statementFactory;


    @Autowired
    TransformAccountStatementToExpenseData transformAccountStatementToExpenseData;
    @Test
    void testImportingAllAvailableStatements(){
        AccountStatement ex = statementFactory.getStatement(AccountStatementName.BankICICIThoraipakkamDinesh);
        ex.getAllRows().forEach(System.out::println);
        assertTrue(ex.getAllRows().size() > 0);

    }

    @Test
    void testIciciBankStatementToTransacationRowConversion(){
        AccountStatement ex = statementFactory.getStatement(AccountStatementName.BankICICIThoraipakkamDinesh);
        transformAccountStatementToExpenseData.transform(ex).forEach(System.out::println);
        assertTrue(transformAccountStatementToExpenseData.transform(ex).size()>0);

    }
}
