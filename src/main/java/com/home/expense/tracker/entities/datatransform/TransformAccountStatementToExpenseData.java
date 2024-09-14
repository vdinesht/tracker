package com.home.expense.tracker.entities.datatransform;

import com.home.expense.tracker.entities.datasource.TransactionDataRow;
import com.home.expense.tracker.entities.statementimport.AccountStatement;

import java.util.List;

public interface TransformAccountStatementToExpenseData {
    List<TransactionDataRow> transform(AccountStatement statement);
}
