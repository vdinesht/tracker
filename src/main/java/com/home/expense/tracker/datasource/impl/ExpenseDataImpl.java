package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.core.PrimaryAccount;
import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.datasource.TransactionDataReader;
import com.home.expense.tracker.datasource.TransactionData;
import com.home.expense.tracker.datasource.TransactionDataWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExpenseDataImpl implements TransactionData {
    private final Logger logger = LoggerFactory.getLogger(ExpenseDataImpl.class);

    private List<TransactionDataRow> dataRows = new ArrayList<>();
    @Autowired
    TransactionDataReader expense2024Reader;

    @Autowired
    TransactionDataWriter expenseWriter;

    public List<TransactionDataRow> getAllRows()  {
        if (dataRows.isEmpty())
            dataRows.addAll(expense2024Reader.getAllRows());

        return dataRows;
    }

    @Override
    public List<TransactionDataRow> getRows(LocalDate from, LocalDate to) {
        return getAllRows().stream().filter(e->  e.date().isAfter(from) && e.date().isBefore(to)).collect(Collectors.toList());
    }


    @Override
    public List<TransactionDataRow> getDebitRows(LocalDate from, LocalDate to, PrimaryAccount acc) {
        return getAllRows().stream().filter(e->  e.date().isAfter(from) && e.date().isBefore(to) && e.debitAccount() == acc).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDataRow> getCreditRows(LocalDate from, LocalDate to, PrimaryAccount acc) {
        return getAllRows().stream().filter(e->  e.date().isAfter(from) && e.date().isBefore(to) && e.creditAccount() == acc).collect(Collectors.toList());
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
        List<TransactionDataRow> listOfRowsToAdd = findNewRows(dataRows).stream().sorted(Comparator.comparingLong(s -> s.date().toEpochDay())).toList();
        for (TransactionDataRow row : listOfRowsToAdd) {
            listOfNewRows.add(newDataRow(row, rowIndex++));
        }
        getAllRows().addAll(listOfNewRows);
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
    public boolean saveAll() {
        return expenseWriter.saveAll(getAllRows());
    }

    private int getMaxIndex(){
        return  getAllRows().stream().mapToInt(TransactionDataRow::id).max().getAsInt();
    }

    private List<TransactionDataRow> findNewRows(List<TransactionDataRow> listRows) {
        logger.info("Number of new rows being requested for addition :" + listRows.size());
        Set<TransactionDataRow> setExistingTransactionRows = new HashSet<>(getAllRows());
        Set<TransactionDataRow> setNewTransactionRows = new HashSet<>(listRows);
        logger.info("Number of new rows after addition to set :" + setNewTransactionRows.size());

        List<TransactionDataRow> tempNewList = listRows.stream().collect(Collectors.groupingBy(Function.identity()))
                        .entrySet()
                        .stream()
                        .filter(e -> e.getValue().size() > 1)
                        .map(Map.Entry::getKey)
                        .toList();

        logger.info("Duplicate rows being added are : ");
        logger.info(tempNewList.toString());

        setExistingTransactionRows.retainAll(setNewTransactionRows);
        logger.info("Number of duplicate rows found :" + setExistingTransactionRows.size());
        logger.info(setExistingTransactionRows.toString());

        setNewTransactionRows.removeAll(setExistingTransactionRows);
        logger.info("Number of new rows being added :" + setNewTransactionRows.size());

        return new ArrayList<>(setNewTransactionRows);
    }

}
