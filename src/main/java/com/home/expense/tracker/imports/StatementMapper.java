package com.home.expense.tracker.imports;

public interface StatementMapper {
    StatementMappingRow getCreditMatcher(AccountStatementName statementOf, String transactionText);
    StatementMappingRow getDebitMatcher(AccountStatementName statementOf, String transactionText);
}
