package com.home.expense.tracker.boundryobjects.reports.Impl;

import com.home.expense.tracker.boundryobjects.reports.ReportRequest;
import com.home.expense.tracker.boundryobjects.reports.ReportResponse;
import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.datasource.TransactionDataRow;

import java.time.LocalDate;
import java.util.List;

public class ReportResponseImpl implements ReportResponse {

    private LocalDate startDate;
    private LocalDate endDate;

    private PrimaryAccount primaryAccount;
    private SubAccount subAccount;

    private List<TransactionDataRow> debits;

    private List<TransactionDataRow> credits;

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

    @Override
    public List<TransactionDataRow> getDebits() {
        return this.debits;
    }

    @Override
    public List<TransactionDataRow> getCredits() {
        return this.credits;
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

    public void setDebits(List<TransactionDataRow> debits) {
        this.debits = debits;
    }

    public void setCredits(List<TransactionDataRow> credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "ReportResponseImpl{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", primaryAccount=" + primaryAccount +
                ", subAccount=" + subAccount +
                '}';
    }

    public ReportResponseImpl(ReportRequest request){
        this.setStartDate(request.fromDate());
        this.setEndDate(request.toDate());
        this.setPrimaryAccount(request.primaryAccount());
        this.setSubAccount(request.subAccount());
    }
}
