package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.datasource.TransactionDataHeader;
import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.datasource.TransactionDataReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionDataCSVReaderImpl implements TransactionDataReader {
    private final Logger logger = LoggerFactory.getLogger(TransactionDataCSVReaderImpl.class);
    @Autowired
    private Environment env;
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
            Reader in = new FileReader(Objects.requireNonNull(env.getProperty("tracker.datasource.file")), StandardCharsets.UTF_8);
            CSVFormat cSVFormat = CSVFormat.DEFAULT.builder()
                                            .setHeader(TransactionDataHeader.class)
                                            .setSkipHeaderRecord(true)
                                            .build();
            Iterable<CSVRecord> records =  CSVParser.parse(in, cSVFormat);
            records.forEach(r-> dataRows.add(MapCSVRecordToTransactionDataRow.transform(r)));
            in.close();
        } catch (IOException ex){
            logger.error(ex.toString());
        }
        logger.info("Records read from CSV File: " + dataRows.size());
        return dataRows;
    }
}
