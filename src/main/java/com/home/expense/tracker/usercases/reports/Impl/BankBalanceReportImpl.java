package com.home.expense.tracker.usercases.reports.Impl;

import com.home.expense.tracker.boundryobjects.reports.Impl.ReportResponseImpl;
import com.home.expense.tracker.boundryobjects.reports.ReportRequest;
import com.home.expense.tracker.boundryobjects.reports.ReportResponse;
import com.home.expense.tracker.entities.datasource.TransactionData;
import com.home.expense.tracker.usercases.reports.BankBalanceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BankBalanceReportImpl implements BankBalanceReport {

    @Autowired
    private TransactionData transactionData;

    @Override
    public ReportResponse getTransactions(ReportRequest request) {
        ReportResponseImpl reportResponse = new ReportResponseImpl(request);
        reportResponse.setCredits(transactionData.getCreditRows(request.fromDate(), request.toDate(),request.primaryAccount())
                                                    .stream().filter(e->e.creditSubAccount() == request.subAccount()).collect(Collectors.toList()));

        reportResponse.setDebits(transactionData.getDebitRows(request.fromDate(), request.toDate(),request.primaryAccount())
                .stream().filter(e->e.debitSubAccount() == request.subAccount()).collect(Collectors.toList()));
        return reportResponse;
    }


}
