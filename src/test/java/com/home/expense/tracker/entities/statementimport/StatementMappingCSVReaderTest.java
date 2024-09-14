package com.home.expense.tracker.entities.statementimport;

import com.home.expense.tracker.entities.statementimport.StatementMappingReader;
import com.home.expense.tracker.entities.statementimport.impl.StatementMappingCSVReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StatementMappingCSVReaderTest {

    private final StatementMappingReader statementMappingReader = new StatementMappingCSVReader();
    @Test
    void testStatementMappingReader(){
        statementMappingReader.getAllDebitMappingRows().forEach(System.out::println);
        statementMappingReader.getAllCreditMappingRows().forEach(System.out::println);
        assertTrue(true);
    }

}
