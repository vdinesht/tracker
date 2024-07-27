package com.home.expense.tracker.statementimport.impl;

import com.home.expense.tracker.core.TransactionCurrency;
import com.home.expense.tracker.statementimport.AccountStatementRow;
import com.home.expense.tracker.statementimport.StatementTransactionType;

import java.time.LocalDate;

public class ICICIStatementRowImpl implements AccountStatementRow {

    private int row;
    private LocalDate valueDate;
    private LocalDate transactionDate;
    private final TransactionCurrency transactionCurrency = TransactionCurrency.INR;
    private String chequeNumber;
    private String transactionRemarks;
    private Double withdrawAmount;
    private Double depositAmount;
    private Double balance;

    private StatementTransactionType transactionType = StatementTransactionType.Debit;


    @Override
    public TransactionCurrency currency() {
        return this.transactionCurrency;
    }

    @Override
    public String transactionDescription() {
        return this.transactionRemarks;
    }

    @Override
    public Double transactionAmount() {
        if (transactionType == StatementTransactionType.Debit)
            return this.withdrawAmount;
        else
            return this.depositAmount;
    }

    @Override
    public StatementTransactionType transactionType() {
        return this.transactionType;
    }
    @Override
    public LocalDate transactionDate() {
        return this.transactionDate;
    }
    @Override
    public int sno() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    public void setWithdrawAmount(Double withdrawAmount) {
        if (withdrawAmount == 0)
            this.transactionType = StatementTransactionType.Credit;
        this.withdrawAmount = withdrawAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        if (depositAmount == 0)
            this.transactionType = StatementTransactionType.Debit;
        this.depositAmount = depositAmount;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ICICIStatementRowImpl{" +
                "row=" + row +
                ", valueDate=" + valueDate +
                ", transactionDate=" + transactionDate +
                ", transactionCurrency=" + transactionCurrency +
                ", chequeNumber='" + chequeNumber + '\'' +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                ", withdrawAmount=" + withdrawAmount +
                ", depositAmount=" + depositAmount +
                ", balance=" + balance +
                ", transactionType=" + transactionType +
                '}';
    }
}
