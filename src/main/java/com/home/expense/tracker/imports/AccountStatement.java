package com.home.expense.tracker.imports;

import java.util.List;

public interface AccountStatement {
    AccountStatementName statementName();
    List<AccountStatementRow> getAllRows();

}
