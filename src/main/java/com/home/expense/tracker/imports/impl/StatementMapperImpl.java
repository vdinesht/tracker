package com.home.expense.tracker.imports.impl;

import com.home.expense.tracker.core.AccountStatement;
import com.home.expense.tracker.imports.StatementMapper;
import com.home.expense.tracker.imports.StatementMappingReader;
import com.home.expense.tracker.imports.StatementMatch;

public class StatementMapperImpl implements StatementMapper {

    private final StatementMappingReader statementMappingReader = new StatementMappingCSVReader();

    @Override
    public StatementMatch getCreditMatcher(AccountStatement statementOf, String transactionText){

        return null;
    }

    @Override
    public StatementMatch getDebitMatcher(AccountStatement statementOf, String transactionText) {
        return null;
    }


}
