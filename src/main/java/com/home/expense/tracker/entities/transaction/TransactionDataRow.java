package com.home.expense.tracker.entities.transaction;

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

    String details();
    int id();
    void id(int i);
    void debitAccount (PrimaryAccount debitAccount);
    void creditAccount (PrimaryAccount creditAccount);
    void debitSubAccount(SubAccount debitSubAccount);
    void creditSubAccount(SubAccount creditSubAccount);

}
