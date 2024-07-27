package com.home.expense.tracker.statementimport.impl;

import com.home.expense.tracker.statementimport.AccountStatement;
import com.home.expense.tracker.statementimport.AccountStatementFactory;
import com.home.expense.tracker.statementimport.AccountStatementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountStatementFactoryImpl implements AccountStatementFactory {
    @Autowired
    List<AccountStatement> availableStatements;

    @Override
    public AccountStatement getStatement(String statementFilePath) {
        AccountStatement accountStatement = null;
        AccountStatementType accountStatementType = AccountStatementFinder.findStatementType(statementFilePath);

        for(AccountStatement s: availableStatements) {
            if (s.statementName() == accountStatementType) {
                accountStatement = s;
                accountStatement.readAllRows(statementFilePath);
            }
        }
        return accountStatement;
    }
}
