package com.home.expense.tracker.statementimport;

import java.util.List;

public interface StatementMappingReader {
    List<StatementMappingRow> getAllDebitMappingRows();
    List<StatementMappingRow> getAllCreditMappingRows();

}
