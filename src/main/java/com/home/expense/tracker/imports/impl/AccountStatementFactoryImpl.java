package com.home.expense.tracker.imports.impl;

import com.home.expense.tracker.imports.AccountStatementName;
import com.home.expense.tracker.imports.AccountStatement;
import com.home.expense.tracker.imports.AccountStatementFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountStatementFactoryImpl implements AccountStatementFactory {
    @Autowired
    List<AccountStatement> availableStatements;
    @Override
    public AccountStatement getStatement(AccountStatementName name) {
        AccountStatement accountStatement = null;
        for(AccountStatement s: availableStatements) {
            if (s.statementName() == name)
                accountStatement = s;
        }
        return accountStatement;
    }
}
