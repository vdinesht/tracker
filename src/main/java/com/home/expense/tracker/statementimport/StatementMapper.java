package com.home.expense.tracker.statementimport;

public interface StatementMapper {
    StatementMappingRow getCreditMatcher(AccountStatementType statementOf, String transactionText);
    StatementMappingRow getDebitMatcher(AccountStatementType statementOf, String transactionText);
}
