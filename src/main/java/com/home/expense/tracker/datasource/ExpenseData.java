package com.home.expense.tracker.datasource;

import com.home.expense.tracker.core.PrimaryAccount;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseData {
    List<DataRow2024> getRows(LocalDate from, LocalDate to);
    List<DataRow2024> getDebitRows(LocalDate from, LocalDate to, PrimaryAccount type);
    List<DataRow2024> getCreditRows(LocalDate from, LocalDate to, PrimaryAccount type);

    double getDebit(LocalDate from, LocalDate to, PrimaryAccount type);
    double getCredit(LocalDate from, LocalDate to, PrimaryAccount type);
}
