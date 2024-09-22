package com.home.expense.tracker.entities.datasource.impl;

import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.datasource.TransactionData;
import com.home.expense.tracker.entities.datasource.TransactionDataReader;
import com.home.expense.tracker.entities.datasource.TransactionDataRow;
import com.home.expense.tracker.entities.datasource.TransactionDataWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class TransactionDataImpl implements TransactionData {
    private final Logger logger = LoggerFactory.getLogger(TransactionDataImpl.class);

    private List<TransactionDataRow> dataRows = new ArrayList<>();
    private final LocalDate accountKeepingFirstDate = LocalDate.of(2000,1,1);
    @Autowired
    private Environment env;

     public List<TransactionDataRow> getAllRows()  {
        if (dataRows.isEmpty())
            loadData();

        return dataRows;
    }

    private void loadData() {
        TransactionDataReader transactionDataReader = new TransactionDataCSVReaderImpl(GetTransactionDataSourceFileName.getDataSourceFilePath(env));
        logger.info("Reading Expense Data from file: " + GetTransactionDataSourceFileName.getDataSourceFilePath(env));
        dataRows.addAll(transactionDataReader.getAllRows());
        logger.info("Expense Data Row Count read: " + getAllRows().size());
    }

    @Override
    public List<TransactionDataRow> getRows(LocalDate from, LocalDate to) {
        return getAllRows().stream().filter(e-> isBetweenDates(e,from,to)).collect(Collectors.toList());
    }
    private static boolean isBetweenDates(TransactionDataRow row, LocalDate from, LocalDate to){
        return row.date().isAfter(from.minusDays(1)) && row.date().isBefore(to.plusDays(1));
    }
    @Override
    public List<TransactionDataRow> getRows(List<Integer> listRowId) {
        return getAllRows().stream().filter(e-> listRowId.contains(e.id())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDataRow> getDebitRows(LocalDate from, LocalDate to, PrimaryAccount acc) {
        return getAllRows().stream().filter(e-> isBetweenDates(e,from,to) && e.debitAccount() == acc).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDataRow> getCreditRows(LocalDate from, LocalDate to, PrimaryAccount acc) {
        return getAllRows().stream().filter(e-> isBetweenDates(e,from,to) && e.creditAccount() == acc).collect(Collectors.toList());
    }

    @Override
    public double getDebitSum(LocalDate from, LocalDate to, PrimaryAccount type) {
        return getDebitRows(from, to,type).stream().mapToDouble(TransactionDataRow::amount).sum();
    }

    @Override
    public double getDebitSum(LocalDate from, LocalDate to, PrimaryAccount type, SubAccount subAccount) {
        return getDebitRows(from, to,type).stream().filter(e->e.debitSubAccount() == subAccount).mapToDouble(TransactionDataRow::amount).sum();
    }

    @Override
    public double getCreditSum(LocalDate from, LocalDate to, PrimaryAccount type, SubAccount subAccount) {
        return getCreditRows(from, to,type).stream().filter(e->e.creditSubAccount() == subAccount).mapToDouble(TransactionDataRow::amount).sum();
    }

    @Override
    public double getDebitSum(PrimaryAccount type, SubAccount subAccount, LocalDate until) {
        return getDebitSum(accountKeepingFirstDate,until, type, subAccount);
    }

    @Override
    public double getCreditSum(PrimaryAccount type, SubAccount subAccount, LocalDate until) {
        return getCreditSum(accountKeepingFirstDate, until, type, subAccount);
    }

    @Override
    public double getCreditSum(LocalDate from, LocalDate to, PrimaryAccount type) {
        return getCreditRows(from, to,type).stream().mapToDouble(TransactionDataRow::amount).sum();
    }

    @Override
    public List<TransactionDataRow> addRows(List<TransactionDataRow> dataRows) {
        logger.info("Expense Data Row Count before adding: " + getAllRows().size());
        getAllRows().addAll(dataRows);
        sortAndUpdateRowId();
        logger.info("Expense Data Row Count after adding: " + getAllRows().size());
        return dataRows;
    }

    @Override
    public TransactionDataRow deleteRow(int rowId) {
        Optional<TransactionDataRow> rowToDelete = getAllRows().stream().filter(e->e.id()== rowId).findFirst();
        TransactionDataRow deletedRow;
        deletedRow = rowToDelete.map(TransactionDataRowImpl::new).orElseGet(() -> new TransactionDataRowImpl(0));
        getAllRows().removeIf(e->e.id()== rowId);

        return deletedRow;
    }

    @Override
    public boolean updateRow(int rowId, TransactionDataRow row) {
        deleteRow(rowId);
        if (addRows(List.of(row)).size() > 0)
            return true;
        else
            return false;
    }

    private void sortAndUpdateRowId() {
        getAllRows().sort(Comparator.comparingLong(s -> s.date().toEpochDay()));
        AtomicInteger row = new AtomicInteger(0);
        getAllRows().forEach(e->e.id(row.addAndGet(1)));
    }

    @Override
    public boolean saveAll() {
        TransactionDataWriter transactionDataWriter = new TransactionDataCSVWriterImpl(GetTransactionDataSourceFileName.getDataSourceFilePath(env));
        logger.info("Saving Expense Data to file: " + GetTransactionDataSourceFileName.getDataSourceFilePath(env));
        sortAndUpdateRowId();
        return transactionDataWriter.saveAll(getAllRows());
    }

    @Override
    public long count() {
        return getAllRows().size();
    }


}
