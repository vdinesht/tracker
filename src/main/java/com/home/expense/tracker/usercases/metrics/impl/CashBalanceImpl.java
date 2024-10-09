package com.home.expense.tracker.usercases.metrics.impl;

import com.home.expense.tracker.entities.transaction.TransactionData;
import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.usercases.metrics.CashBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CashBalanceImpl implements CashBalance {

    @Autowired
    TransactionData expenseData;

    @Override
    public double getBalance(LocalDate from, LocalDate to) {
        return expenseData.getDebitSum(from, to, PrimaryAccount.CashAsset) - expenseData.getCreditSum(from, to, PrimaryAccount.CashAsset);
    }
}
