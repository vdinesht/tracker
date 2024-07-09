package com.home.expense.tracker.imports;

import com.home.expense.tracker.core.TransactionCurrency;

import java.time.LocalDate;

public interface AccountStatementRow {
    int sno();
    LocalDate transactionDate();
    TransactionCurrency currency();
    String transactionDescription();
    Double transactionAmount();
    StatementTransactionType transactionType();
}
