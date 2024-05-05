package com.home.expense.tracker.metrics.impl;

import com.home.expense.tracker.datasource.ExpenseData;
import com.home.expense.tracker.datasource.PrimaryAccount;
import com.home.expense.tracker.metrics.CashBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CashBalanceImpl implements CashBalance {

    @Autowired
    ExpenseData expenseData;

    @Override
    public double getBalance(LocalDate from, LocalDate to) {
        return expenseData.getDebit(from, to, PrimaryAccount.CashAsset) - expenseData.getCredit(from, to, PrimaryAccount.CashAsset);
    }
}
