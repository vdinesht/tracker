package com.home.expense.tracker.statementimport;

public interface AccountStatementFactory {
    AccountStatement getStatement(String statementFilePath);
}
