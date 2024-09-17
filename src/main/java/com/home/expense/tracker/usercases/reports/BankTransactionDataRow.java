package com.home.expense.tracker.usercases.reports;

import com.home.expense.tracker.entities.GroupTag;
import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.TransactionCurrency;

import java.time.LocalDate;

public interface BankTransactionDataRow {
    LocalDate date();
    double amount();
    TransactionCurrency currency();
    String description();
    PrimaryAccount debitAccount();
    PrimaryAccount creditAccount();
    SubAccount debitSubAccount();
    SubAccount creditSubAccount();

    GroupTag groupTag();
    String gdriveLink();
    int id();

    double balance();
    void balance(double balance);
}
