package com.home.expense.tracker.imports.ICICI.Impl;

import com.home.expense.tracker.imports.ICICI.ICICIBankStatementRow;

import java.time.LocalDate;

public class StatementRowImpl implements ICICIBankStatementRow {

    private int row;
    private LocalDate valueDate;
    private LocalDate transactionDate;
    private String chequeNumber;
    private String transactionRemarks;
    private Double withdrawAmount;
    private Double depositAmount;
    private Double balance;

    @Override
    public int sno() {
        return this.row;
    }

    @Override
    public LocalDate valueDate() {
        return this.valueDate;
    }

    @Override
    public LocalDate transactionDate() {
        return this.transactionDate;
    }

    @Override
    public String chequeNo() {
        return this.chequeNumber;
    }

    @Override
    public String transactionRemarks() {
        return this.transactionRemarks;
    }

    @Override
    public Double withdrawAmount() {
        return this.withdrawAmount;
    }

    @Override
    public Double depositAmount() {
        return this.depositAmount;
    }

    @Override
    public Double balance() {
        return this.balance;
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
        this.withdrawAmount = withdrawAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "StatementRowImpl{" +
                "row=" + row +
                ", valueDate=" + valueDate +
                ", transactionDate=" + transactionDate +
                ", chequeNumber='" + chequeNumber + '\'' +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                ", withdrawAmount=" + withdrawAmount +
                ", depositAmount=" + depositAmount +
                ", balance=" + balance +
                '}';
    }
}
