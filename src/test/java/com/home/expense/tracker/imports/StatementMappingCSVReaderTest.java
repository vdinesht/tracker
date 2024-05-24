package com.home.expense.tracker.imports;

import com.home.expense.tracker.imports.impl.StatementMappingCSVReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StatementMappingCSVReaderTest {

    private StatementMappingReader statementMappingReader = new StatementMappingCSVReader();
    @Test
    void testStatementMappingReader(){
        statementMappingReader.getAllDebitMappingRows().stream().forEach(System.out::println);
        statementMappingReader.getAllCreditMappingRows().stream().forEach(System.out::println);
        assertTrue(true);
    }

}
