package com.home.expense.tracker.entities.transaction;

import java.util.List;

public interface TransactionDataReader {
    List<TransactionDataRow> getAllRows();
}
