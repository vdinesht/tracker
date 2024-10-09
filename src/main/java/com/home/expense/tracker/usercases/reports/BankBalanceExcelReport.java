package com.home.expense.tracker.usercases.reports;

import com.home.expense.tracker.entities.SubAccount;

import java.time.LocalDate;

public interface BankBalanceExcelReport {
    void generateReport(SubAccount bankAccount, LocalDate from, LocalDate to, String fileName);
}
