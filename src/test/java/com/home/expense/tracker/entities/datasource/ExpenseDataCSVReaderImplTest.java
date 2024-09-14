package com.home.expense.tracker.entities.datasource;

import com.home.expense.tracker.entities.datasource.TransactionDataReader;
import com.home.expense.tracker.entities.datasource.impl.TransactionDataCSVReaderImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static com.home.expense.tracker.entities.datasource.impl.GetTransactionDataSourceFileName.getDataSourceFilePath;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ExpenseDataCSVReaderImplTest {

    @Autowired
    private Environment env;
    @Test
    void testCSVReading(){
        TransactionDataReader transactionDataReader = new TransactionDataCSVReaderImpl(getDataSourceFilePath(env));
        System.out.println("Reading Expense Data from file: " + env.getProperty("tracker.datasource.file"));
        System.out.println("Expense Data Row Count read from file: " + transactionDataReader.getAllRows().size());
        System.out.println(transactionDataReader.getAllRows().get(12503));
        assertTrue(transactionDataReader.getAllRows().size()>0);

    }
}
