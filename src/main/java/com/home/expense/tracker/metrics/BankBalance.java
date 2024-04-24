package com.home.expense.tracker.metrics;

import java.time.LocalDate;

public interface BankBalance {
    double getBalance(LocalDate from, LocalDate to);

}
