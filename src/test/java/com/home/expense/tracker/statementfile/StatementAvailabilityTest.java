package com.home.expense.tracker.statementfile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StatementAvailabilityTest {

    @Autowired
    StatementFileAvailability statementFiles;

    @Autowired
    StatementFileTransfer statementFileTransfer;


    @Test
    void testIfStatementsAvailableToProcess() {

        assertTrue(!statementFiles.getNextAvailableFilePath().isEmpty());

    }

    @Test
    void testAvailableStatementFileList() {
        assertTrue(statementFiles.getNextAvailableFilePath().contains(".csv"));
    }

    @Test
    void testMovingCSVFiles()  {
        String filePath = statementFiles.getNextAvailableFilePath();
        while (!filePath.isEmpty()){
            System.out.println("Moving file : " + filePath);
            waitFor(30);
            statementFileTransfer.completedStatementFileProcessing(filePath);
            System.out.println("Move completed : "+ filePath);
            filePath = statementFiles.getNextAvailableFilePath();
        }

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