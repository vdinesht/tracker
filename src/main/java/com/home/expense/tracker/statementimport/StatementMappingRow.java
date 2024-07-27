package com.home.expense.tracker.statementimport;

import com.home.expense.tracker.core.PrimaryAccount;
import com.home.expense.tracker.core.SubAccount;

public interface StatementMappingRow {

    AccountStatementType statement();
    String token();
    PrimaryAccount debitAccount();
    PrimaryAccount creditAccount();
    SubAccount debitSubAccount();
    SubAccount creditSubAccount();
}
