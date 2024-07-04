package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.core.PrimaryAccount;
import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.datasource.TransactionDataReader;
import com.home.expense.tracker.datasource.TransactionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseDataImpl implements TransactionData {
    private final Logger logger = LoggerFactory.getLogger(ExpenseDataImpl.class);
    @Autowired
    TransactionDataReader expense2024Reader;

    @Override
    public List<TransactionDataRow> getRows(LocalDate from, LocalDate to) {
        return expense2024Reader.getAllRows().stream().filter(e->  e.date().isAfter(from) && e.date().isBefore(to)).collect(Collectors.toList());
    }

    @Override
    public boolean saveToFile(String fullFilePath) {
        return false;
    }

    @Override
    public List<TransactionDataRow> getDebitRows(LocalDate from, LocalDate to, PrimaryAccount acc) {
        return expense2024Reader.getAllRows().stream().filter(e->  e.date().isAfter(from) && e.date().isBefore(to) && e.debitAccount() == acc).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDataRow> getCreditRows(LocalDate from, LocalDate to, PrimaryAccount acc) {
        return expense2024Reader.getAllRows().stream().filter(e->  e.date().isAfter(from) && e.date().isBefore(to) && e.creditAccount() == acc).collect(Collectors.toList());
    }

    @Override
    public double getDebitSum(LocalDate from, LocalDate to, PrimaryAccount type) {
        return getDebitRows(from, to,type).stream().mapToDouble(TransactionDataRow::amount).sum();
    }

    @Override
    public double getCreditSum(LocalDate from, LocalDate to, PrimaryAccount type) {
        return getCreditRows(from, to,type).stream().mapToDouble(TransactionDataRow::amount).sum();
    }

    @Override
    public List<TransactionDataRow> addRows(List<TransactionDataRow> dataRows) {
        int rowIndex = getMaxIndex()+1;
        List<TransactionDataRow> listOfNewRows = new ArrayList<>();
        for (TransactionDataRow e : dataRows) {
            if (!isDuplicateRow(e)) {
                listOfNewRows.add(newDataRow(e, rowIndex++));
             }
        }
        return listOfNewRows;
    }

    private TransactionDataRow newDataRow(TransactionDataRow r, int rowIndex){
        TransactionDataRowImpl newRow = new TransactionDataRowImpl();
        newRow.setId(rowIndex);
        newRow.setDate(r.date());
        newRow.setAmount(r.amount());
        newRow.setCurrency(r.currency());
        newRow.setDescription(r.description());
        newRow.setDebitAccount(r.debitAccount());
        newRow.setCreditAccount(r.creditAccount());
        newRow.setDebitSubAccount(r.debitSubAccount());
        newRow.setCreditSubAccount(r.creditSubAccount());
        newRow.setGroupTag(r.groupTag());
        newRow.setGdriveLink(r.gdriveLink());
        return newRow;
    }
    @Override
    public List<TransactionDataRow> findConflictingRows(List<TransactionDataRow> listRows) {
        return expense2024Reader.getAllRows().stream().filter(e->isDuplicateRow(e)).collect(Collectors.toList());
    }

    private boolean isDuplicateRow(TransactionDataRow dataRow){
        return expense2024Reader.getAllRows().stream()
                .anyMatch(e->e.date().isEqual(dataRow.date()) && isSameAmount(e.amount(), dataRow.amount()) &&
                            e.debitAccount() == dataRow.debitAccount() && e.creditAccount() == dataRow.creditAccount());
    }

    private boolean isSameAmount(double a, double b){
        if (Math.abs(a-b) > 0.1)
            return false;
        else
            return true;
    }

    private int getMaxIndex(){
        return  expense2024Reader.getAllRows().stream().mapToInt(e -> e.id()).max().getAsInt();
    }
}
