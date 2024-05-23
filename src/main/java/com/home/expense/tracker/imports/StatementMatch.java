package com.home.expense.tracker.imports;

import com.home.expense.tracker.core.AccountStatement;
import com.home.expense.tracker.core.PrimaryAccount;
import com.home.expense.tracker.core.SubAccount;

public interface StatementMatch {
    AccountStatement statement();
    String token();
    PrimaryAccount debitAccount();
    PrimaryAccount creditAccount();
    SubAccount debitSubAccount();
    SubAccount creditSubAccount();

}
