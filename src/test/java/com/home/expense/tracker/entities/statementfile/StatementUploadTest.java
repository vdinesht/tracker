package com.home.expense.tracker.entities.statementfile;

import com.home.expense.tracker.entities.datasource.TransactionData;
import com.home.expense.tracker.entities.datasource.TransactionDataRow;
import com.home.expense.tracker.entities.datatransform.TransformAccountStatementToExpenseData;
import com.home.expense.tracker.entities.datatransform.impl.FindDuplicatesInExpenseData;
import com.home.expense.tracker.entities.statementfile.StatementFileAvailability;
import com.home.expense.tracker.entities.statementfile.StatementFileTransfer;
import com.home.expense.tracker.entities.statementimport.AccountStatement;
import com.home.expense.tracker.entities.statementimport.AccountStatementFactory;
import com.home.expense.tracker.entities.statementimport.AccountStatementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;
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



    void testForDuplicatesAfterAdding(){
        String filePath = "C:\\Temp\\ExpenseTracker\\StatementFolder\\OpTransactionHistory1Jan2024To31Jul2024.csv";
        List<TransactionDataRow> duplicateRows = getDuplicateRows(filePath);
        System.out.println("Duplicate IDs count: " +duplicateRows.size());
        assertTrue(duplicateRows.size() == 497*2);

        //testDelete();
        //saveExpenseData();
    }

    private List<TransactionDataRow> getDuplicateRows(String filePath) {
        List<TransactionDataRow> rowsToAdd = getTransactionDataRows(filePath);
        transactionData.addRows(rowsToAdd);

        List<TransactionDataRow> duplicateRows = findConflictsInTransactionDataRows(rowsToAdd);
        return duplicateRows;
    }

    private List<TransactionDataRow> findConflictsInTransactionDataRows(List<TransactionDataRow> rowsToAdd) {
        List<Integer> duplicateList = FindDuplicatesInExpenseData.findMatchingRowsExcludingDescription(transactionData, rowsToAdd.get(0).date());
        List<TransactionDataRow> duplicateRows = transactionData.getRows(duplicateList);
        return duplicateRows;
    }

    private List<TransactionDataRow> getTransactionDataRows(String filePath) {
        AccountStatement statement = accountStatementFactory.getStatement(filePath);
        assertTrue(statement.statementName() != AccountStatementType.Unknown);

        List<TransactionDataRow> rowsToAdd = transformAccountStatementToExpenseData.transform(statement);
        rowsToAdd.sort(Comparator.comparing(e->e.date().toEpochDay()));
        return rowsToAdd;
    }

    void testDelete(){
        System.out.println("Expense Data Rows before delete: " + transactionData.count());
        System.out.println(transactionData.deleteRow(0).toString());
        System.out.println("Expense Data Rows after delete: " + transactionData.count());

    }

    void saveExpenseData(){
        assertTrue(transactionData.saveAll());
    }
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


    void uploadICICIStatements(){
        String filePath = "C:\\Temp\\ExpenseTracker\\StatementFolder\\.csv";
        List<TransactionDataRow> newRows = getTransactionDataRows(filePath);
        System.out.println("New rows found in statement: " +newRows.size());
        List<TransactionDataRow> duplicateRows = findConflictsInTransactionDataRows(newRows);
        System.out.println("Conflict of New Rows in existing Expense Data: " +duplicateRows.size());
        duplicateRows.forEach(System.out::println);

        if (duplicateRows.size() == 75){
            System.out.println("Read to Save Data to file...");
            System.out.println("Performing validations...");
            List<TransactionDataRow> addedRows = transactionData.addRows(newRows);

            if (addedRows.size() == newRows.size()){
                System.out.println("Ready to save to file");
               // saveExpenseData();
            }
        }
    }

}
