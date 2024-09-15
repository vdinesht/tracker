package com.home.expense.tracker.usecases.excelreports;

import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.usercases.excelreports.BankBalanceExcelReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestBankBalanceExcelReport {

    @Autowired
    BankBalanceExcelReport bankBalanceExcelReport;

    @Test
    void testBankBalanceExcelReport(){
        String strSaveFile = "C:\\Temp\\ExpenseTracker\\StatementFolder\\test1.csv";
        bankBalanceExcelReport.generateReport(SubAccount.Bank_ICICI_Thoraipakkam_Dinesh,
                LocalDate.of(2022,1,1), LocalDate.of(2022, 1, 31),
                strSaveFile);

        assertTrue(true);
    }

}
