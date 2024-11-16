package com.home.expense.tracker.entities.statementimport;

import com.home.expense.tracker.entities.transaction.TransformAccountStatementToExpenseData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountStatementToExpenseDataTransformTest {
    @Autowired
    AccountStatementFactory statementFactory;
    @Autowired
    TransformAccountStatementToExpenseData transformAccountStatementToExpenseData;

    @Test
    void testImportingAllAvailableStatements(){
        AccountStatement ex = statementFactory.getStatement(statementFileName());
        ex.getAllRows().forEach(System.out::println);
        assertTrue(ex.getAllRows().size() > 0);

    }

    @Test
    void testIciciBankStatementToTransacationRowConversion(){
        AccountStatement ex = statementFactory.getStatement(statementFileName());
        transformAccountStatementToExpenseData.transform(ex).forEach(System.out::println);
        assertTrue(transformAccountStatementToExpenseData.transform(ex).size()>0);

    }

    private static String statementFileName(){
        Path resourcePath;
        try {
            resourcePath = Paths.get(Objects.requireNonNull(AccountStatementToExpenseDataTransformTest.class.getResource("/")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Source Folder Path: " + resourcePath);
        return resourcePath + "//OpTransactionHistory1JanTo31Dec2023UTF8.csv";
    }
}
