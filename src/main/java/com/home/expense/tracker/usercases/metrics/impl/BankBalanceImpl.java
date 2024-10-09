package com.home.expense.tracker.usercases.metrics.impl;

import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.transaction.TransactionData;
import com.home.expense.tracker.usercases.metrics.BankBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BankBalanceImpl implements BankBalance {
    @Autowired
    TransactionData expenseData;
    @Override
    public double getBalance(SubAccount bankAccount, LocalDate from, LocalDate to) {
        return expenseData.getDebitSum(from, to, PrimaryAccount.BankAsset,bankAccount) - expenseData.getCreditSum(from, to, PrimaryAccount.BankAsset, bankAccount);
    }

    @Override
    public double getBalance(SubAccount bankAccount, LocalDate until) {
        return expenseData.getDebitSum(PrimaryAccount.BankAsset,bankAccount,until) - expenseData.getCreditSum(PrimaryAccount.BankAsset, bankAccount, until);
    }


}
