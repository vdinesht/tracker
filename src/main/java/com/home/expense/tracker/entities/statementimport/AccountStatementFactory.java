package com.home.expense.tracker.entities.statementimport;

public interface AccountStatementFactory {
    AccountStatement getStatement(String statementFilePath);
}
