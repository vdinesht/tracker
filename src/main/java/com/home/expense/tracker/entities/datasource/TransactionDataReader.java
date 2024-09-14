package com.home.expense.tracker.entities.datasource;

import java.util.List;

public interface TransactionDataReader {
    List<TransactionDataRow> getAllRows();
}
