package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.core.SubAccount;
import com.home.expense.tracker.core.TransactionCurrency;
import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.entities.GroupTag;
import com.home.expense.tracker.core.PrimaryAccount;

import java.time.LocalDate;
import java.util.Objects;

public class TransactionDataRowImpl implements TransactionDataRow {

    private LocalDate date;
    private double amount;
    private TransactionCurrency currency;
    private String description;
    private PrimaryAccount debitAccount;
    private PrimaryAccount creditAccount;
    private SubAccount debitSubAccount;
    private SubAccount creditSubAccount;
    private String transType2015;

    private GroupTag groupTag;
    private String gdriveLink;
    private int id;

    public TransactionDataRowImpl(int i) {
        this.id = i;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency(TransactionCurrency currency) {
        this.currency = currency;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDebitAccount(PrimaryAccount debitAccount) {
        this.debitAccount = debitAccount;
    }

    public void setCreditAccount(PrimaryAccount creditAccount) {
        this.creditAccount = creditAccount;
    }

    public void setDebitSubAccount(SubAccount debitSubAccount) {
        this.debitSubAccount = debitSubAccount;
    }

    public void setCreditSubAccount(SubAccount creditSubAccount) {
        this.creditSubAccount = creditSubAccount;
    }

    public void setTransType2015(String transType2015) {
        this.transType2015 = transType2015;
    }

    public void setGroupTag(GroupTag groupTag) {
        this.groupTag = groupTag;
    }

    public void setGdriveLink(String gdriveLink) {
        this.gdriveLink = gdriveLink;
    }

    public void id(int i) {
        this.id = i;
    }

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
    public String transType2015() {
        return this.transType2015;
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
    public String toString() {
        return "TransactionDataRowImpl{" +
                "date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", debitAccount=" + debitAccount +
                ", creditAccount=" + creditAccount +
                ", debitSubAccount='" + debitSubAccount + '\'' +
                ", creditSubAccount='" + creditSubAccount + '\'' +
                ", transType2015='" + transType2015 + '\'' +
                ", groupTag=" + groupTag +
                ", gdriveLink='" + gdriveLink + '\'' +
                ", id=" + id +
                '}';
    }

    public TransactionDataRowImpl(TransactionDataRow other) {
        this.date = other.date();
        this.amount = other.amount();
        this.currency = other.currency();
        this.description = other.description();
        this.debitAccount = other.debitAccount();
        this.creditAccount = other.creditAccount();
        this.debitSubAccount = other.debitSubAccount();
        this.creditSubAccount = other.creditSubAccount();
        this.transType2015 = other.transType2015();
        this.groupTag = other.groupTag();
        this.gdriveLink = other.gdriveLink();
        this.id = other.id();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDataRowImpl that = (TransactionDataRowImpl) o;
        return Double.compare(that.amount, amount) == 0 && date.equals(that.date) && currency == that.currency && description.equals(that.description) && debitAccount == that.debitAccount && creditAccount == that.creditAccount && debitSubAccount == that.debitSubAccount && creditSubAccount == that.creditSubAccount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, currency, description, debitAccount, creditAccount, debitSubAccount, creditSubAccount);
    }
}
