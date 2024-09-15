package com.home.expense.tracker.usercases.excelreports.Impl;

import com.home.expense.tracker.boundryobjects.reports.Impl.ReportRequestImpl;
import com.home.expense.tracker.boundryobjects.reports.ReportResponse;
import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.usercases.excelreports.BankBalanceExcelReport;
import com.home.expense.tracker.usercases.metrics.BankBalance;
import com.home.expense.tracker.usercases.reports.BankBalanceReport;
import com.home.expense.tracker.usercases.reports.BankTransactionDataRow;
import com.home.expense.tracker.usercases.reports.Impl.BankTransactionCSVWriter;
import com.home.expense.tracker.usercases.reports.Impl.BankTransactionDataRowImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BankBalanceExcelReportImpl implements BankBalanceExcelReport {

    @Autowired
    private BankBalanceReport bankBalanceReport;

    @Autowired
    private BankBalance bankBalance;

    @Override
    public void generateReport(SubAccount bankAccount, LocalDate from, LocalDate to, String fileName) {
        ReportResponse response = getReport(bankAccount, from, to);
        BankTransactionCSVWriter writer = new BankTransactionCSVWriter(fileName);
        List<BankTransactionDataRow> listAllRows = getBankTransactionDataRows(bankAccount, response);
        writer.saveAll(listAllRows);
    }

    private List<BankTransactionDataRow> getBankTransactionDataRows(SubAccount bankAccount, ReportResponse response) {
        List<BankTransactionDataRow> listAllRows = new ArrayList<>();
        listAllRows.addAll(response.getCredits().stream().map(BankTransactionDataRowImpl::new).toList());
        listAllRows.addAll(response.getDebits().stream().map(BankTransactionDataRowImpl::new).toList());
        listAllRows.sort(Comparator.comparingLong(s -> s.date().toEpochDay()));
        AtomicLong atomicLong = new AtomicLong(Math.round(bankBalance.getBalance(bankAccount,response.fromDate().minusDays(1))*100));
        listAllRows.forEach(e-> {
            if (e.debitAccount() == PrimaryAccount.BankAsset && e.debitSubAccount() == bankAccount)//Income - add balance
                e.balance(atomicLong.addAndGet(Math.round(e.amount()*100)));
            else if (e.creditAccount() == PrimaryAccount.BankAsset && e.creditSubAccount() == bankAccount) //expense - reduce balance
                e.balance(atomicLong.addAndGet(Math.round(e.amount()*-100)));
            });
        return listAllRows;
    }

    private ReportResponse getReport(SubAccount bankAccount, LocalDate from, LocalDate to) {
        ReportRequestImpl reportRequest = new ReportRequestImpl();
        reportRequest.setStartDate(from);
        reportRequest.setEndDate(to);
        reportRequest.setPrimaryAccount(PrimaryAccount.BankAsset);
        reportRequest.setSubAccount(bankAccount);
        return bankBalanceReport.getTransactions(reportRequest);
    }
}
