package com.home.expense.tracker.datatransform;

import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.statementimport.AccountStatement;

import java.util.List;

public interface TransformAccountStatementToExpenseData {
    List<TransactionDataRow> transform(AccountStatement statement);
}
