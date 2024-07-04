package com.home.expense.tracker.datasource;

import com.home.expense.tracker.core.GroupTag;
import com.home.expense.tracker.core.PrimaryAccount;

import java.time.LocalDate;

public interface TransactionDataRow {
    LocalDate date();
    double amount();
    String currency();
    String description();
    PrimaryAccount debitAccount();
    PrimaryAccount creditAccount();
    String debitSubAccount();
    String creditSubAccount();
    String transType2015();

    GroupTag groupTag();
    String gdriveLink();
    int id();
}
