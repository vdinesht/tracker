package com.home.expense.tracker.datasource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class Expense2024CSVReaderImplTest {
    @Autowired
    TransactionDataReader transactionDataReader;
    @Test
    void testCSVReading(){
        List<TransactionDataRow> dataRows = transactionDataReader.getAllRows();
        System.out.println(dataRows.get(12503));
        assertTrue(dataRows.size()>0);

    }

    @Test
    void testTransactionHeaderEnum(){
        TransactionDataHeader  transactionDataHeader;
        System.out.println(TransactionDataHeader.Amount);
        System.out.println(TransactionDataHeader.Amount.name());
        System.out.println(TransactionDataHeader.Amount.toString());
    }
}
