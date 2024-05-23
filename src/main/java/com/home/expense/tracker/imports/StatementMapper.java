package com.home.expense.tracker.imports;

import com.home.expense.tracker.core.AccountStatement;

public interface StatementMapper {
    StatementMatch getCreditMatcher(AccountStatement statementOf, String transactionText);
    StatementMatch getDebitMatcher(AccountStatement statementOf, String transactionText);
}
