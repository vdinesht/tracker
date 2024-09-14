package com.home.expense.tracker.entities.datasource;

import com.home.expense.tracker.entities.GroupTag;
import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.TransactionCurrency;

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
