package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.core.PrimaryAccount;
import com.home.expense.tracker.datasource.TransactionData;
import com.home.expense.tracker.datasource.TransactionDataReader;
import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.datasource.TransactionDataWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.home.expense.tracker.datasource.impl.GetTransactionDataSourceFileName.getDataSourceFilePath;

@Service
public class TransactionDataImpl implements TransactionData {
    private final Logger logger = LoggerFactory.getLogger(TransactionDataImpl.class);

    private List<TransactionDataRow> dataRows = new ArrayList<>();

    @Autowired
    private Environment env;

     public List<TransactionDataRow> getAllRows()  {
        if (dataRows.isEmpty())
            loadData();

        return dataRows;
    }

    private void loadData() {
        TransactionDataReader transactionDataReader = new TransactionDataCSVReaderImpl(getDataSourceFilePath(env));
        logger.info("Reading Expense Data from file: " + env.getProperty("tracker.datasource.file"));
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
    public List<TransactionDataRow> getRows(List<Integer> listIds) {
        return getAllRows().stream().filter(e-> listIds.contains(e.id())).collect(Collectors.toList());
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
    public TransactionDataRow deleteRow(int row) {
        Optional<TransactionDataRow> rowToDelete = getAllRows().stream().filter(e->e.id()==row).findFirst();
        TransactionDataRow deletedRow;
        deletedRow = rowToDelete.map(TransactionDataRowImpl::new).orElseGet(() -> new TransactionDataRowImpl(0));
        getAllRows().removeIf(e->e.id()==row);

        return deletedRow;
    }

    private void sortAndUpdateRowId() {
        getAllRows().sort(Comparator.comparingLong(s -> s.date().toEpochDay()));
        AtomicInteger row = new AtomicInteger(0);
        getAllRows().forEach(e->e.id(row.addAndGet(1)));
    }

    @Override
    public boolean saveAll() {
        TransactionDataWriter transactionDataWriter = new TransactionDataCSVWriterImpl(Objects.requireNonNull(env.getProperty("tracker.datasource.file")));
        logger.info("Saving Expense Data to file: " + env.getProperty("tracker.datasource.file"));
        sortAndUpdateRowId();
        return transactionDataWriter.saveAll(getAllRows());
    }

    @Override
    public long count() {
        return getAllRows().size();
    }


}
