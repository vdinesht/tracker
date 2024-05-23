package com.home.expense.tracker.imports.impl;

import com.home.expense.tracker.core.AccountStatement;
import com.home.expense.tracker.core.PrimaryAccount;
import com.home.expense.tracker.core.SubAccount;
import com.home.expense.tracker.imports.StatementMatch;

import javax.swing.*;

public class StatementMatchImpl implements StatementMatch {
    AccountStatement statementOf;
    String matchingToken;
    PrimaryAccount debitAccount;
    PrimaryAccount creditAccount;

    SubAccount debitSubAccount;
    SubAccount creditSubAccount;

    public void setStatementOf(AccountStatement statementOf) {
        this.statementOf = statementOf;
    }

    public void setMatchingToken(String matchingToken) {
        this.matchingToken = matchingToken;
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

    @Override
    public AccountStatement statement() {
        return this.statementOf;
    }

    @Override
    public String token() {
        return this.matchingToken;
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
}
