package com.home.expense.tracker.imports;

import com.home.expense.tracker.core.AccountStatement;

public interface StatementMapper {
    StatementMappingRow getCreditMatcher(AccountStatement statementOf, String transactionText);
    StatementMappingRow getDebitMatcher(AccountStatement statementOf, String transactionText);
}
