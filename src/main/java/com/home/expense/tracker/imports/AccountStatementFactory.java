package com.home.expense.tracker.imports;

public interface AccountStatementFactory {
    AccountStatement getStatement(AccountStatementName name);
}
