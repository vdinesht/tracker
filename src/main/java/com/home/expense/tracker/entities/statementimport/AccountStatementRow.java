package com.home.expense.tracker.entities.statementimport;

import com.home.expense.tracker.entities.TransactionCurrency;

import java.time.LocalDate;

public interface AccountStatementRow {
    LocalDate transactionDate();
    TransactionCurrency currency();
    String transactionDescription();
    Double transactionAmount();
    StatementTransactionType transactionType();

    void setTransactionDescription(String description);
}
