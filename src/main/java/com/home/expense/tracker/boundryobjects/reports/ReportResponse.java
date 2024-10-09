package com.home.expense.tracker.boundryobjects.reports;

import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.transaction.TransactionDataRow;

import java.time.LocalDate;
import java.util.List;

public interface ReportResponse {
    LocalDate fromDate();
    LocalDate toDate();

    PrimaryAccount primaryAccount();
    SubAccount subAccount();
    List<TransactionDataRow> getDebits();
    List<TransactionDataRow> getCredits();
}
