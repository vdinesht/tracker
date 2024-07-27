package com.home.expense.tracker.datasource;

import com.home.expense.tracker.core.PrimaryAccount;

import java.time.LocalDate;
import java.util.List;

public interface TransactionData {
    List<TransactionDataRow> getRows(LocalDate from, LocalDate to);

    List<TransactionDataRow> getDebitRows(LocalDate from, LocalDate to, PrimaryAccount type);
    List<TransactionDataRow> getCreditRows(LocalDate from, LocalDate to, PrimaryAccount type);

    double getDebitSum(LocalDate from, LocalDate to, PrimaryAccount type);
    double getCreditSum(LocalDate from, LocalDate to, PrimaryAccount type);

    List<TransactionDataRow> addRows(List<TransactionDataRow> dataRows);

    List<TransactionDataRow> findConflictingRows(List<TransactionDataRow> listRows);

    boolean savelAll();
}
