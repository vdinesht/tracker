package com.home.expense.tracker.boundryobjects.reports;

import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;

import java.time.LocalDate;

public interface ReportRequest {
    LocalDate fromDate();
    LocalDate toDate();
    PrimaryAccount primaryAccount();

    SubAccount subAccount();
}
