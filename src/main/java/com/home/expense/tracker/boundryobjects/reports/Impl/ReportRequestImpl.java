package com.home.expense.tracker.boundryobjects.reports.Impl;

import com.home.expense.tracker.boundryobjects.reports.ReportRequest;
import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;

import java.time.LocalDate;

public class ReportRequestImpl implements ReportRequest {

    private LocalDate startDate;
    private LocalDate endDate;

    private PrimaryAccount primaryAccount;
    private SubAccount subAccount;

    @Override
    public LocalDate fromDate() {
        return this.startDate;
    }

    @Override
    public LocalDate toDate() {
        return this.endDate;
    }

    @Override
    public PrimaryAccount primaryAccount() {
        return this.primaryAccount;
    }

    @Override
    public SubAccount subAccount() {
        return this.subAccount;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setPrimaryAccount(PrimaryAccount primaryAccount) {
        this.primaryAccount = primaryAccount;
    }

    public void setSubAccount(SubAccount subAccount) {
        this.subAccount = subAccount;
    }

      @Override
    public String toString() {
        return "ReportRequestImpl{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", primaryAccount=" + primaryAccount +
                ", subAccount=" + subAccount +
                '}';
    }
}
