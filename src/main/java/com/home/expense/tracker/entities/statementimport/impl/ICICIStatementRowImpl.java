package com.home.expense.tracker.entities.statementimport.impl;

import com.home.expense.tracker.entities.TransactionCurrency;
import com.home.expense.tracker.entities.statementimport.AccountStatementRow;
import com.home.expense.tracker.entities.statementimport.StatementTransactionType;

import java.time.LocalDate;
import java.util.Objects;

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
    public void setTransactionDescription(String description) {
        setTransactionRemarks(description);
    }

    @Override
    public LocalDate transactionDate() {
        return this.transactionDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ICICIStatementRowImpl that = (ICICIStatementRowImpl) o;
        return transactionDate.equals(that.transactionDate) && transactionCurrency == that.transactionCurrency && transactionRemarks.equals(that.transactionRemarks) && withdrawAmount.equals(that.withdrawAmount) && depositAmount.equals(that.depositAmount) && transactionType == that.transactionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, transactionCurrency, transactionRemarks, withdrawAmount, depositAmount, transactionType);
    }
}
