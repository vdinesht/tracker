package com.home.expense.tracker.metrics;

import com.home.expense.tracker.statementimport.AccountStatementType;

import java.time.LocalDate;

public interface BankBalance {
    double getBalance(AccountStatementType statementType, LocalDate from, LocalDate to);

}
