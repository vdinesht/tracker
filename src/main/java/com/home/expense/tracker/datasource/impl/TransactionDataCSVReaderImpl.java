package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.datasource.TransactionDataHeader;
import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.datasource.TransactionDataReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionDataCSVReaderImpl implements TransactionDataReader {
    private final Logger logger = LoggerFactory.getLogger(TransactionDataCSVReaderImpl.class);
    private final String transactionsDataFile = "C:\\Temp\\ExpenseTracker\\OurHomeTransactionsDataUTF8V1.csv";

    private List<TransactionDataRow> dataRows = new ArrayList<>();

    @Override
    public List<TransactionDataRow> getAllRows()  {
        if (dataRows.isEmpty())
            return fillDataRows();
        else
            return dataRows;
    }

    private List<TransactionDataRow> fillDataRows() {
        try{
            Reader in = new FileReader(transactionsDataFile, StandardCharsets.UTF_8);
            CSVFormat cSVFormat = CSVFormat.DEFAULT.builder()
                                            .setHeader(TransactionDataHeader.class)
                                            .setSkipHeaderRecord(true)
                                            .build();
            Iterable<CSVRecord> records =  CSVParser.parse(in, cSVFormat);
            records.forEach(r-> dataRows.add(MapCSVRecordToTransactionDataRow.transform(r)));
            in.close();
        }
        catch (FileNotFoundException ex){
            logger.error(ex.toString());
        }
        catch (IOException ioException) {
            logger.error(ioException.toString());
        }
        logger.info("Records read from CSV File: " + dataRows.size());
        return dataRows;
    }
}
