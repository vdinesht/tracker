package com.home.expense.tracker.datasource;

import java.util.List;

public interface TransactionDataWriter {
    boolean saveAll(List<TransactionDataRow> allRows);
    boolean addAll(List<TransactionDataRow> allRows);
}
