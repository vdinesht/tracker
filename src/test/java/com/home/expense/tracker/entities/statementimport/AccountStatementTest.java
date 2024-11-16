package com.home.expense.tracker.entities.statementimport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountStatementTest {
    @Autowired
    AccountStatementFactory statementFactory;
    @Autowired
    StatementMapper statementMapper;

    private int count = 0;

    @Test
    void testAccountStatementReader(){

        try {
            Path resourcePath = Paths.get(Objects.requireNonNull(AccountStatementTest.class.getResource("/")).toURI());
            System.out.println("Source Folder Path: " + resourcePath);
            String statementFile = resourcePath + "//OpTransactionHistory1JanTo31Dec2023UTF8.csv";

            AccountStatement statement = statementFactory.getStatement(statementFile);
            assertTrue(statement.getAllRows().size()>0);

            //All credit Rows
            statement.getAllRows().stream().filter(e->e.transactionType()== StatementTransactionType.Credit).forEach(e-> { System.out.println(e.transactionDescription());
                System.out.println(statementMapper.getCreditMatcher(AccountStatementType.BankICICIThoraipakkamDinesh,
                        e.transactionDescription()).token());
                ++count;});

            //All debit Rows
            statement.getAllRows().stream().filter(e->e.transactionType()== StatementTransactionType.Debit).forEach(e-> { System.out.println(e.transactionDescription());
                System.out.println(statementMapper.getDebitMatcher(AccountStatementType.BankICICIThoraipakkamDinesh,
                        e.transactionDescription()).token());
                ++count;});

            System.out.println("Total ICICI Statement line processed: " + count);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

}
