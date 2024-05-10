package com.home.expense.tracker.metrics.impl;

import com.home.expense.tracker.datasource.ExpenseData;
import com.home.expense.tracker.core.PrimaryAccount;
import com.home.expense.tracker.metrics.BankBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BankBalanceImpl implements BankBalance {
    @Autowired
    ExpenseData expenseData;
    @Override
    public double getBalance(LocalDate from, LocalDate to) {
        return expenseData.getDebit(from, to, PrimaryAccount.BankAsset) - expenseData.getCredit(from, to, PrimaryAccount.BankAsset);
    }
}
