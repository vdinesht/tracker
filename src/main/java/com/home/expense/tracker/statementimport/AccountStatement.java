package com.home.expense.tracker.statementimport;

import java.util.List;

public interface AccountStatement {
    AccountStatementType statementName();
    List<AccountStatementRow> readAllRows(String filePath);
    List<AccountStatementRow> getAllRows();
}
