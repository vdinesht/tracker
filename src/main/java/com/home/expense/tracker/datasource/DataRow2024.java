package com.home.expense.tracker.datasource;

import java.time.LocalDate;

public interface DataRow2024 {
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
