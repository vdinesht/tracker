package com.home.expense.tracker.metrics;

import java.time.LocalDate;

public interface CashBalance {
    double getBalance(LocalDate from, LocalDate to);
}
