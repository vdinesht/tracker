package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.core.PrimaryAccount;
import com.home.expense.tracker.core.SubAccount;
import com.home.expense.tracker.datasource.TransactionDataRow;

public class KeyTransactionDataRow {
    private final long epochDays;
    private final long amount;
    private final PrimaryAccount debitAccount;
    private final PrimaryAccount creditAccount;
    private final SubAccount debitSubAccount;
    private final SubAccount creditSubAccount;
    private final int id;

    public KeyTransactionDataRow(TransactionDataRow dataRow){
        this.epochDays = dataRow.date().EPOCH.toEpochDay();
        this.amount = Math.round(dataRow.amount());
        this.debitAccount = dataRow.debitAccount();
        this.creditAccount = dataRow.creditAccount();
        this.debitSubAccount = dataRow.debitSubAccount();
        this.creditSubAccount = dataRow.creditSubAccount();
        this.id = dataRow.id();
    }


    public long getAmount() {
        return amount;
    }

    public PrimaryAccount getDebitAccount() {
        return debitAccount;
    }

    public PrimaryAccount getCreditAccount() {
        return creditAccount;
    }

    public SubAccount getDebitSubAccount() {
        return debitSubAccount;
    }

    public SubAccount getCreditSubAccount() {
        return creditSubAccount;
    }

    public int getId() {
        return id;
    }

    public long getEpochDays() {
        return epochDays;
    }

    @Override
    public String toString() {
        return "KeyTransactionDataRow{" +
                "epochDays=" + epochDays +
                ", amount=" + amount +
                ", debitAccount=" + debitAccount +
                ", creditAccount=" + creditAccount +
                ", debitSubAccount=" + debitSubAccount +
                ", creditSubAccount=" + creditSubAccount +
                ", id=" + id +
                '}';
    }
}
