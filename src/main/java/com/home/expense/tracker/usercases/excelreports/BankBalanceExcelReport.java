package com.home.expense.tracker.usercases.excelreports;

import com.home.expense.tracker.entities.SubAccount;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public interface BankBalanceExcelReport {
    void generateReport(SubAccount bankAccount, LocalDate from, LocalDate to, String fileName);
}
