package com.home.expense.tracker.entities.datasource;

import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.datasource.impl.TransactionDataRowImpl;
import com.home.expense.tracker.entities.datatransform.impl.FindDuplicatesInExpenseData;
import com.home.expense.tracker.usercases.excelreports.BankBalanceExcelReport;
import com.home.expense.tracker.usercases.metrics.BankBalance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TransactionDataTest {
    @Autowired
    TransactionData expenseData;
    @Autowired
    BankBalanceExcelReport bankBalanceExcelReport;
    @Autowired
    BankBalance bankBalance;
    @Test
    void testCreditRows(){
        LocalDate from = LocalDate.of(2021,1,1);
        LocalDate to = LocalDate.of(2024,5,31);
        System.out.println("Cash Expenses since 1 Jan 2021 : " + expenseData.getCreditSum(from,to, PrimaryAccount.CashAsset));
        System.out.println("Cash Income since 1 Jan 2021 : " + expenseData.getDebitSum(from,to,PrimaryAccount.CashAsset));

        assertEquals(0, expenseData.getCreditSum(from, to, PrimaryAccount.CashAsset) - expenseData.getCreditRows(from, to, PrimaryAccount.CashAsset).stream().mapToDouble(TransactionDataRow::amount).sum());
        assertEquals(0, expenseData.getDebitSum(from, to, PrimaryAccount.CashAsset) - expenseData.getDebitRows(from, to, PrimaryAccount.CashAsset).stream().mapToDouble(TransactionDataRow::amount).sum());

    }

    @Test
    void testIfAbleToIdentifyDuplicateDateAndAmountInStatementfile(){
        LocalDate from = LocalDate.of(2021,1,1);
        assertTrue(FindDuplicatesInExpenseData.findMatchingRowsExcludingDescription(expenseData, from).size() > 0);
    }


    private void testAddingOneRecord(){
        TransactionDataRowImpl dataRow = new TransactionDataRowImpl(0);
        dataRow.setAmount(1269478.33D);
        dataRow.setDate(LocalDate.of(2021,12,31));
        dataRow.setCreditAccount(PrimaryAccount.DigitalPay);
        dataRow.setDebitAccount(PrimaryAccount.BankAsset);
        dataRow.setDescription("ICICI Thoraipakkam - Dinesh -Opening Balance Adjustment ( 429625.18 + 839853.15)");
        dataRow.setDebitSubAccount(SubAccount.Bank_ICICI_Thoraipakkam_Dinesh);
        dataRow.setCreditSubAccount(SubAccount.Digital_Misc);

        List<TransactionDataRow> dataRowList = Arrays.asList(dataRow);
        long initialCount = expenseData.count();
        expenseData.addRows(dataRowList);
        assertEquals(expenseData.count(), initialCount+1);

        System.out.println("Bank balance: " + bankBalance.getBalance(SubAccount.Bank_ICICI_Thoraipakkam_Dinesh,LocalDate.of(2021,1,1), LocalDate.of(2021,12,31)));

        System.out.println("Bank balance: " + bankBalance.getBalance(SubAccount.Bank_ICICI_Thoraipakkam_Dinesh,LocalDate.of(2021,1,1), LocalDate.of(2022,1,31)));

        //expenseData.saveAll();

    }



    void toPerformDataCleanUp(){
        InsertBalanceAdjustmentRow();

        System.out.print("Bank Balance: ");
        System.out.println(bankBalance.getBalance(SubAccount.Bank_ICICI_Thoraipakkam_Dinesh, LocalDate.of(2021,12,31)));
        assertTrue(Math.abs(bankBalance.getBalance(SubAccount.Bank_ICICI_Thoraipakkam_Dinesh, LocalDate.of(2021,12,31)) - 429625.18D) < 1);

        List<Integer> listOfRowIdToDelete = List.of(12403, 12407, 12415, 12457, 12458);
        listOfRowIdToDelete.forEach(e->expenseData.deleteRow(e));

        saveExpenseDataToTestFile();

        assertTrue(expenseData.saveAll());
    }


    private void saveExpenseDataToTestFile(){
      String strSaveFile = "C:\\Temp\\ExpenseTracker\\StatementFolder\\test1.csv";
       bankBalanceExcelReport.generateReport(SubAccount.Bank_ICICI_Thoraipakkam_Dinesh,
                LocalDate.of(2022,1,1), LocalDate.of(2022, 1, 31),
                strSaveFile);

        assertTrue(true);
    }

    private void InsertBalanceAdjustmentRow() {
        TransactionDataRowImpl dataRow = new TransactionDataRowImpl(0);
        dataRow.setAmount(6690766.65D);
        dataRow.setDate(LocalDate.of(2021,12,31));
        dataRow.setCreditAccount(PrimaryAccount.DigitalPay);
        dataRow.setDebitAccount(PrimaryAccount.BankAsset);
        dataRow.setDescription("ICICI Thoraipakkam - Dinesh -Opening Balance Adjustment ( 429625.18 + 6261141.47)");
        dataRow.setDebitSubAccount(SubAccount.Bank_ICICI_Thoraipakkam_Dinesh);
        dataRow.setCreditSubAccount(SubAccount.Digital_Misc);

        List<TransactionDataRow> dataRowList = Arrays.asList(dataRow);
        expenseData.addRows(dataRowList);
    }


}
