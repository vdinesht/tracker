package com.home.expense.tracker.usercases.metrics.impl;

import com.home.expense.tracker.entities.datasource.TransactionData;
import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.usercases.metrics.BankBalance;
import com.home.expense.tracker.entities.statementimport.AccountStatementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BankBalanceImpl implements BankBalance {
    @Autowired
    TransactionData expenseData;
    @Override
    public double getBalance(AccountStatementType statementType, LocalDate from, LocalDate to) {
        return expenseData.getDebitSum(from, to, PrimaryAccount.BankAsset) - expenseData.getCreditSum(from, to, PrimaryAccount.BankAsset);
    }


}
