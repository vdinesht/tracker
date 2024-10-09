package com.home.expense.tracker.entities.transaction;

import com.home.expense.tracker.entities.transaction.impl.TransactionDataCSVReaderImpl;
import com.home.expense.tracker.entities.transaction.impl.TransactionDataCSVWriterImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SavingTransactionDataReadBackTest {
    @Autowired
    TransactionData expenseData;

    @Test
    void readBackTest(){
        //Generate Random 1000 records
        Random random = new Random();
        List<Integer> randomRowIdList = random.ints(1,Integer.parseInt(String.valueOf(expenseData.count()))).limit(1000).boxed().collect(Collectors.toList());

        List<TransactionDataRow> randomRecords = expenseData.getRows(randomRowIdList);

        System.out.println("Test Expense Data Row Count being written: " + randomRecords.size());
        String testWriterPath = "C:\\Temp\\ExpenseTracker\\Test\\OurHomeTransactionsDataUTF8T1V2.csv";
        System.out.println("Writing Test Expense Data to file: " + testWriterPath);
        TransactionDataWriter transactionDataWriter = new TransactionDataCSVWriterImpl(testWriterPath);
        assertTrue( transactionDataWriter.saveAll(randomRecords));

        //Read back
        TransactionDataReader transactionDataReader = new TransactionDataCSVReaderImpl(testWriterPath);
        System.out.println("Reading Test Expense Data from file: " + testWriterPath);
        System.out.println("Test Expense Data Row Count read from file: " + transactionDataReader.getAllRows().size());

        Set<TransactionDataRow> writenTestSet = new HashSet<>(randomRecords);
        Set<TransactionDataRow> readBackTestSet = new HashSet<>(transactionDataReader.getAllRows());
        assertTrue(readBackTestSet.removeAll(writenTestSet));
        assertEquals(0, readBackTestSet.size()); //If records match exactly same

    }
}
