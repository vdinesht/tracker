package com.home.expense.tracker.statementimport.impl;

import com.home.expense.tracker.statementimport.AccountStatementType;
import com.home.expense.tracker.core.PrimaryAccount;
import com.home.expense.tracker.core.SubAccount;
import com.home.expense.tracker.statementimport.StatementMappingRow;

public class StatementMappingRowImpl implements StatementMappingRow {
    AccountStatementType statement;
    String token;
    PrimaryAccount debitAccount;
    PrimaryAccount creditAccount;
    SubAccount debitSubAccount;
    SubAccount creditSubAccount;



    public void setStatement(AccountStatementType statement) {
        this.statement = statement;
    }


    public void setToken(String token) {
        this.token = token;
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

    public StatementMappingRowImpl(AccountStatementType statement, String token, PrimaryAccount debitAccount, PrimaryAccount creditAccount, SubAccount debitSubAccount, SubAccount creditSubAccount) {
        this.statement = statement;
        this.token = token;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.debitSubAccount = debitSubAccount;
        this.creditSubAccount = creditSubAccount;
    }

    @Override
    public AccountStatementType statement() {
        return this.statement;
    }

    @Override
    public String token() {
        return this.token;
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
    public String toString() {
        return "StatementMappingRowImpl{" +
                "statement=" + statement +
                ", token='" + token + '\'' +
                ", debitAccount=" + debitAccount +
                ", creditAccount=" + creditAccount +
                ", debitSubAccount=" + debitSubAccount +
                ", creditSubAccount=" + creditSubAccount +
                '}';
    }
}
