package com.home.expense.tracker.statementimport;

import com.home.expense.tracker.core.TransactionCurrency;

import java.time.LocalDate;

public interface AccountStatementRow {
    LocalDate transactionDate();
    TransactionCurrency currency();
    String transactionDescription();
    Double transactionAmount();
    StatementTransactionType transactionType();

    void setTransactionDescription(String description);
}
