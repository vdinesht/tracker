package com.home.expense.tracker.metrics.impl;

import com.home.expense.tracker.metrics.CashBalance;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CashBalanceImpl implements CashBalance {
    @Override
    public double getBalance(LocalDate from, LocalDate to) {
        return 1;
    }
}
