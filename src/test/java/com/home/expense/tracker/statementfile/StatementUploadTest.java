package com.home.expense.tracker.statementfile;

import com.home.expense.tracker.datasource.TransactionData;
import com.home.expense.tracker.datatransform.TransformAccountStatementToExpenseData;
import com.home.expense.tracker.statementimport.AccountStatement;
import com.home.expense.tracker.statementimport.AccountStatementFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StatementUploadTest {

    @Autowired
    TransactionData transactionData;

    @Autowired
    AccountStatementFactory accountStatementFactory;
    @Autowired
    TransformAccountStatementToExpenseData transformAccountStatementToExpenseData;
    @Autowired
    StatementFileAvailability statementFiles;

    @Autowired
    StatementFileTransfer statementFileTransfer;

    @Test
    void testuploadStatements()  {
        System.out.println("Processing available statements.");
        String filePath = statementFiles.getNextAvailableFilePath();
        while (!filePath.isEmpty()){
            System.out.println("Moving file : " + filePath);
            AccountStatement statement = accountStatementFactory.getStatement(filePath);
            transactionData.addRows(transformAccountStatementToExpenseData.transform(statement));
            assertTrue(transactionData.saveAll());
            waitFor(5);
            statementFileTransfer.completedStatementFileProcessing(filePath);
            System.out.println("Move completed : "+ filePath);
            filePath = statementFiles.getNextAvailableFilePath();
        }

        System.out.println("Completed uploading of available statements.");
        assertTrue(statementFiles.getNextAvailableFilePath().isEmpty());
    }

    private static void waitFor(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


}
