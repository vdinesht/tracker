package com.home.expense.tracker.entities.datatransform.impl;

import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.TransactionCurrency;
import com.home.expense.tracker.entities.datasource.TransactionDataRow;

import java.time.LocalDate;
import java.util.Objects;

public class TransactionDataRowSubSet {
    private final LocalDate date;
    private final double amount;
    private final TransactionCurrency currency;
    private final PrimaryAccount debitAccount;
    private final PrimaryAccount creditAccount;
    private final SubAccount debitSubAccount;
    private final SubAccount creditSubAccount;
    private int id;

    public TransactionDataRowSubSet(TransactionDataRow other) {
        this.date = other.date();
        this.amount = other.amount();
        this.currency = other.currency();
        this.debitAccount = other.debitAccount();
        this.creditAccount = other.creditAccount();
        this.debitSubAccount = other.debitSubAccount();
        this.creditSubAccount = other.creditSubAccount();
        this.id = other.id();
    }

    public int id() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDataRowSubSet that = (TransactionDataRowSubSet) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(date, that.date) && currency == that.currency && debitAccount == that.debitAccount && creditAccount == that.creditAccount && debitSubAccount == that.debitSubAccount && creditSubAccount == that.creditSubAccount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, currency, debitAccount, creditAccount, debitSubAccount, creditSubAccount);
    }

    @Override
    public String toString() {
        return "TransactionDataRowSubSet{" +
                "date=" + date +
                ", amount=" + amount +
                ", currency=" + currency +
                ", debitAccount=" + debitAccount +
                ", creditAccount=" + creditAccount +
                ", debitSubAccount=" + debitSubAccount +
                ", creditSubAccount=" + creditSubAccount +
                ", id=" + id +
                '}';
    }
}
