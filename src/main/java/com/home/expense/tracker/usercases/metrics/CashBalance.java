package com.home.expense.tracker.usercases.metrics;

import java.time.LocalDate;

public interface CashBalance {
    double getBalance(LocalDate from, LocalDate to);
}
