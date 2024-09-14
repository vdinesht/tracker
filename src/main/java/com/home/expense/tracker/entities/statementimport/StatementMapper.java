package com.home.expense.tracker.entities.statementimport;

public interface StatementMapper {
    StatementMappingRow getCreditMatcher(AccountStatementType statementOf, String transactionText);
    StatementMappingRow getDebitMatcher(AccountStatementType statementOf, String transactionText);
}
