package com.home.expense.tracker.datasource;

import java.util.List;

public interface TransactionDataReader {
    List<TransactionDataRow> getAllRows();
}
