package com.home.expense.tracker.usercases.metrics;

import com.home.expense.tracker.entities.SubAccount;

import java.time.LocalDate;

public interface BankBalance {
    double getBalance(SubAccount bankAccount, LocalDate from, LocalDate to);
    double getBalance(SubAccount bankAccount, LocalDate until);

}
