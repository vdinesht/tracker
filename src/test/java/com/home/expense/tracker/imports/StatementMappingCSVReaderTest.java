package com.home.expense.tracker.imports;

import com.home.expense.tracker.imports.impl.StatementMappingCSVReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StatementMappingCSVReaderTest {

    //@Autowired
    //StatementMappingReader statementMappingReader;
    @Test
    void testStatementMappingReader(){
        StatementMappingReader statementMappingReader = new StatementMappingCSVReader();
        statementMappingReader.getAllDebitMappingRows().stream().forEach(System.out::println);
        statementMappingReader.getAllCreditMappingRows().stream().forEach(System.out::println);
        assertTrue(true);
    }

}
