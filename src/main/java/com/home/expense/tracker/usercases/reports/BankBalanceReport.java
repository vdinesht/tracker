package com.home.expense.tracker.usercases.reports;

import com.home.expense.tracker.boundryobjects.reports.ReportRequest;
import com.home.expense.tracker.boundryobjects.reports.ReportResponse;

public interface BankBalanceReport {
    ReportResponse getTransactions(ReportRequest request);
}
