package com.home.expense.tracker.entities.datasource.impl;

import com.home.expense.tracker.entities.csvfile.CSVFileReader;
import com.home.expense.tracker.entities.datasource.TransactionDataHeader;
import com.home.expense.tracker.entities.datasource.TransactionDataReader;
import com.home.expense.tracker.entities.datasource.TransactionDataRow;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TransactionDataCSVReaderImpl extends CSVFileReader<TransactionDataRow, TransactionDataHeader>implements TransactionDataReader {
    private final Logger logger = LoggerFactory.getLogger(TransactionDataCSVReaderImpl.class);

    private List<TransactionDataRow> dataRows = new ArrayList<>();
    private final String filePath;

    public TransactionDataCSVReaderImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<TransactionDataRow> getAllRows()  {
        if (dataRows.isEmpty())
            this.dataRows = readAllRows(this.filePath);

        return this.dataRows;
    }


    @Override
    protected TransactionDataRow transformCSVToTypeRecord(CSVRecord csvRecord) {
        return MapCSVRecordToTransactionDataRow.transform(csvRecord);
    }
}
