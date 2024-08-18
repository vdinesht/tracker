package com.home.expense.tracker.datasource;

import com.home.expense.tracker.core.GroupTag;
import com.home.expense.tracker.core.PrimaryAccount;
import com.home.expense.tracker.core.SubAccount;
import com.home.expense.tracker.core.TransactionCurrency;

import java.time.LocalDate;

public interface TransactionDataRow {
    LocalDate date();
    double amount();
    TransactionCurrency currency();
    String description();
    PrimaryAccount debitAccount();
    PrimaryAccount creditAccount();
    SubAccount debitSubAccount();
    SubAccount creditSubAccount();
    String transType2015();

    GroupTag groupTag();
    String gdriveLink();
    int id();
    void id(int i);
}
