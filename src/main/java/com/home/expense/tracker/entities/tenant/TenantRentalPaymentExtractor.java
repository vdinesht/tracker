package com.home.expense.tracker.entities.tenant;

import com.home.expense.tracker.entities.transaction.TransactionDataRow;

import java.time.LocalDate;
import java.util.List;

public interface TenantRentalPaymentExtractor {
    List<TransactionDataRow> getMatchingTransactions(LocalDate startDate, LocalDate endDate, List<String> tokens);
}
