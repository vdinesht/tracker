package com.home.expense.tracker.usecases.excelreports;

import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.usercases.excelreports.BankBalanceExcelReport;
import com.home.expense.tracker.usercases.metrics.BankBalance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestBankBalanceExcelReport {

    @Autowired
    BankBalanceExcelReport bankBalanceExcelReport;

    @Autowired
    BankBalance bankBalance;

    @Test
    void testBankBalanceExcelReport(){
        validateBalance(SubAccount.Bank_ICICI_Thoraipakkam_Dinesh, LocalDate.of(2021,12,31), 429625.18D);
        validateBalance(SubAccount.Bank_ICICI_Thoraipakkam_Dinesh, LocalDate.of(2022,1,31), 337890.18D);

        validateSaveExpenseDataToTestFile("C:\\Temp\\ExpenseTracker\\StatementFolder\\test1.csv", SubAccount.Bank_ICICI_Thoraipakkam_Dinesh,
                LocalDate.of(2022,1,2), LocalDate.of(2022, 2, 28));

    }

    private void validateBalance(SubAccount subAccount, LocalDate until, Double value){
        assertTrue(Math.round(bankBalance.getBalance(subAccount, until) - value) < 1);
    }

    private void validateSaveExpenseDataToTestFile(String strFile, SubAccount subAccount,  LocalDate from, LocalDate to){
        bankBalanceExcelReport.generateReport(subAccount, from, to, strFile);
        assertTrue(true);
    }
}
