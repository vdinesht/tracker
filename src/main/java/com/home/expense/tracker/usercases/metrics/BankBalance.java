package com.home.expense.tracker.usercases.metrics;

import com.home.expense.tracker.entities.statementimport.AccountStatementType;

import java.time.LocalDate;

public interface BankBalance {
    double getBalance(AccountStatementType statementType, LocalDate from, LocalDate to);

}
