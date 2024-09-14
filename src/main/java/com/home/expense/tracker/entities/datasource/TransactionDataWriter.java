package com.home.expense.tracker.entities.datasource;

import java.util.List;

public interface TransactionDataWriter {
    boolean saveAll(List<TransactionDataRow> allRows);
    boolean addAll(List<TransactionDataRow> allRows);
}
