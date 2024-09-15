package com.home.expense.tracker.entities.datasource;

import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;

import java.time.LocalDate;
import java.util.List;

public interface TransactionData {
    List<TransactionDataRow> getRows(LocalDate from, LocalDate to);
    List<TransactionDataRow> getRows(List<Integer> listIds);

    List<TransactionDataRow> getDebitRows(LocalDate from, LocalDate to, PrimaryAccount type);
    List<TransactionDataRow> getCreditRows(LocalDate from, LocalDate to, PrimaryAccount type);

    double getDebitSum(LocalDate from, LocalDate to, PrimaryAccount type);
    double getDebitSum(LocalDate from, LocalDate to, PrimaryAccount type, SubAccount subAccount);
    double getCreditSum(LocalDate from, LocalDate to, PrimaryAccount type, SubAccount subAccount);
    double getDebitSum(PrimaryAccount type, SubAccount subAccount, LocalDate until);
    double getCreditSum(PrimaryAccount type, SubAccount subAccount, LocalDate until);
    double getCreditSum(LocalDate from, LocalDate to, PrimaryAccount type);
    List<TransactionDataRow> addRows(List<TransactionDataRow> dataRows);
    TransactionDataRow deleteRow(int row);
    boolean saveAll();
    long count();
}
