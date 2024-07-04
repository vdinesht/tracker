package com.home.expense.tracker.datasource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ExpenseDataSourceCSVWriteTest {

    @Autowired
    TransactionDataReader transactionDataReader;


    @Autowired
    TransactionDataWriter transactionDataWriter;

    @Test
    void dataCSVWriteTest(){
        List<TransactionDataRow> dataRows = transactionDataReader.getAllRows();
        assertTrue( transactionDataWriter.saveAll(dataRows));
    }
}
