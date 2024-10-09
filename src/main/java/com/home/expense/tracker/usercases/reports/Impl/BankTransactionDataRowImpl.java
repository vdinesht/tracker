package com.home.expense.tracker.usercases.reports.Impl;

import com.home.expense.tracker.entities.GroupTag;
import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.TransactionCurrency;
import com.home.expense.tracker.entities.transaction.TransactionDataRow;
import com.home.expense.tracker.usercases.reports.BankTransactionDataRow;

import java.time.LocalDate;

public class BankTransactionDataRowImpl implements BankTransactionDataRow {
    private LocalDate date;
    private double amount;
    private TransactionCurrency currency;
    private String description;
    private PrimaryAccount debitAccount;
    private PrimaryAccount creditAccount;
    private SubAccount debitSubAccount;
    private SubAccount creditSubAccount;

    private GroupTag groupTag;
    private String gdriveLink;
    private int id;

    private Double balance;

    @Override
    public LocalDate date() {
        return this.date;
    }

    @Override
    public double amount() {
        return this.amount;
    }

    @Override
    public TransactionCurrency currency() {
        return this.currency;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public PrimaryAccount debitAccount() {
        return this.debitAccount;
    }

    @Override
    public PrimaryAccount creditAccount() {
        return this.creditAccount;
    }

    @Override
    public SubAccount debitSubAccount() {
        return this.debitSubAccount;
    }

    @Override
    public SubAccount creditSubAccount() {
        return this.creditSubAccount;
    }

    @Override
    public GroupTag groupTag() {
        return this.groupTag;
    }

    @Override
    public String gdriveLink() {
        return this.gdriveLink;
    }

    @Override
    public int id() {
        return this.id;
    }

    @Override
    public double balance() {
        return this.balance;
    }

    @Override
    public void balance(double value) { this.balance = value; }

    public BankTransactionDataRowImpl(TransactionDataRow row) {
        this.id = row.id();
        this.date = row.date();
        this.amount = row.amount();
        this.currency = row.currency();
        this.description = row.description();
        this.debitAccount = row.debitAccount();
        this.creditAccount = row.creditAccount();
        this.debitSubAccount = row.debitSubAccount();
        this.creditSubAccount = row.creditSubAccount();
        this.groupTag = row.groupTag();
        this.gdriveLink = row.gdriveLink();
    }

    private void setBalance(double b){
        this.balance = b;
    }
}
