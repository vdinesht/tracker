package com.home.expense.tracker.imports;

import com.home.expense.tracker.imports.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        AccountStatement statement = statementFactory.getStatement(AccountStatementName.BankICICIThoraipakkamDinesh);
        assertTrue(statement.getAllRows().size()>0);

        //All credit Rows
        statement.getAllRows().stream().filter(e->e.transactionType()== StatementTransactionType.Credit).forEach(e-> { System.out.println(e.transactionDescription());
                                                                   System.out.println(statementMapper.getCreditMatcher(AccountStatementName.BankICICIThoraipakkamDinesh,
                                                                           e.transactionDescription()).token());
                                                                    ++count;});


        //All debit Rows
        statement.getAllRows().stream().filter(e->e.transactionType()== StatementTransactionType.Debit).forEach(e-> { System.out.println(e.transactionDescription());
            System.out.println(statementMapper.getDebitMatcher(AccountStatementName.BankICICIThoraipakkamDinesh,
                    e.transactionDescription()).token());
            ++count;});

        System.out.println("Total ICICI Statement line processed: " + count);
    }

}
