package com.home.expense.tracker.imports.ICICI;

import com.home.expense.tracker.core.AccountStatement;
import com.home.expense.tracker.imports.StatementMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StatementCSVReaderTest {
    @Autowired
    ICICIBankStatementReader statementReader;
    @Autowired
    StatementMapper statementMapper;

    private int count = 0;
    @Test
    void testCSVReader(){
        assertTrue(statementReader.getAllRows().size()>0);

        //All credit Rows
        statementReader.getAllCreditRows().stream().forEach(e-> { System.out.println(e.transactionRemarks());
                                                                   System.out.println(statementMapper.getCreditMatcher(AccountStatement.BankICICIThoraipakkamDinesh,
                                                                           e.transactionRemarks()).token());
                                                                    ++count;});


        //All debit Rows
        statementReader.getAllDebitRows().stream().forEach(e-> { System.out.println(e.transactionRemarks());
            System.out.println(statementMapper.getDebitMatcher(AccountStatement.BankICICIThoraipakkamDinesh,
                    e.transactionRemarks()).token());
            ++count;});

        System.out.println("Total ICICI Statement line processed: " + count);
    }

}
