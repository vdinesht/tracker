package com.home.expense.tracker.entities.statementimport;

import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;

public interface StatementMappingRow {

    AccountStatementType statement();
    String token();
    PrimaryAccount debitAccount();
    PrimaryAccount creditAccount();
    SubAccount debitSubAccount();
    SubAccount creditSubAccount();
}
