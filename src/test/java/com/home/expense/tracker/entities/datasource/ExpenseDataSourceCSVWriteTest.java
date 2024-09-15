package com.home.expense.tracker.entities.datasource;

import com.home.expense.tracker.entities.datasource.TransactionDataReader;
import com.home.expense.tracker.entities.datasource.TransactionDataWriter;
import com.home.expense.tracker.entities.datasource.impl.TransactionDataCSVReaderImpl;
import com.home.expense.tracker.entities.datasource.impl.TransactionDataCSVWriterImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.Objects;

import static com.home.expense.tracker.entities.datasource.impl.GetTransactionDataSourceFileName.getDataSourceFilePath;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ExpenseDataSourceCSVWriteTest {

    @Autowired
    private Environment env;

    @Test
    void dataCSVWriteTest(){
        TransactionDataReader transactionDataReader = new TransactionDataCSVReaderImpl(getDataSourceFilePath(env));
        System.out.println("Expense Data Row Count being written: " + transactionDataReader.getAllRows().size());
        String testWriterPath = "C:\\Temp\\ExpenseTracker\\Test\\OurHomeTransactionsDataUTF8TV2.csv";
        System.out.println("Writing Expense Data to file: " + testWriterPath);
        TransactionDataWriter transactionDataWriter = new TransactionDataCSVWriterImpl(testWriterPath);
        assertTrue( transactionDataWriter.saveAll(transactionDataReader.getAllRows()));
    }


}
