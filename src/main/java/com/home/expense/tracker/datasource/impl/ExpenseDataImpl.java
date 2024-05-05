package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.datasource.DataRow2024;
import com.home.expense.tracker.datasource.Expense2024Reader;
import com.home.expense.tracker.datasource.ExpenseData;
import com.home.expense.tracker.datasource.PrimaryAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseDataImpl implements ExpenseData {

    @Autowired
    Expense2024Reader expense2024Reader;

    @Override
    public List<DataRow2024> getRows(LocalDate from, LocalDate to) {
        return expense2024Reader.getAllRows().stream().filter(e->  e.date().isAfter(from) && e.date().isBefore(to)).collect(Collectors.toList());
    }

    @Override
    public List<DataRow2024> getDebitRows(LocalDate from, LocalDate to, PrimaryAccount acc) {
        return expense2024Reader.getAllRows().stream().filter(e->  e.date().isAfter(from) && e.date().isBefore(to) && e.debitAccount() == acc).collect(Collectors.toList());
    }

    @Override
    public List<DataRow2024> getCreditRows(LocalDate from, LocalDate to, PrimaryAccount acc) {
        return expense2024Reader.getAllRows().stream().filter(e->  e.date().isAfter(from) && e.date().isBefore(to) && e.creditAccount() == acc).collect(Collectors.toList());
    }

    @Override
    public double getDebit(LocalDate from, LocalDate to, PrimaryAccount type) {
        return getDebitRows(from, to,type).stream().mapToDouble(DataRow2024::amount).sum();
    }

    @Override
    public double getCredit(LocalDate from, LocalDate to, PrimaryAccount type) {
        return getCreditRows(from, to,type).stream().mapToDouble(DataRow2024::amount).sum();
    }
}
