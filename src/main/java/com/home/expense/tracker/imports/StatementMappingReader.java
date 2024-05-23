package com.home.expense.tracker.imports;

import com.home.expense.tracker.imports.impl.StatementMappingRowImpl;

import java.util.List;

public interface StatementMappingReader {
    List<StatementMappingRow> getAllDebitMappingRows();
    List<StatementMappingRow> getAllCreditMappingRows();

}
