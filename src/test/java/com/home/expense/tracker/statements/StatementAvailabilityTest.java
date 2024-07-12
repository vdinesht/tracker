package com.home.expense.tracker.statements;

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

        if (statementFiles.isAvailable())
            assertTrue(true);
        else
            assertTrue(false);

    }

    @Test
    void testAvailableStatementFileList() {
        assertTrue(statementFiles.getNextAvailableFilePath().contains(".csv"));
    }

    @Test
    void testMovingCSVFiles()  {
        while (statementFiles.isAvailable()){
            String filePath = statementFiles.getNextAvailableFilePath();
            System.out.println("Moving file : " + filePath);
            waitFor(30);
            statementFileTransfer.moveStatementFile(filePath);
            System.out.println("Move completed : "+ filePath);
        }

        assertTrue(!statementFiles.isAvailable());
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