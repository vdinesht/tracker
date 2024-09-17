package com.home.expense.tracker.boundryobjects.reports.csvfile;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class CSVFileReader<T> {
    private final Logger logger = LoggerFactory.getLogger(CSVFileReader.class);

    protected List<T> readAllRows(String headers[], String strFilePath)  {
            return fillDataRows(headers, strFilePath);
    }

    private List<T> fillDataRows(String headers[], String strFilePath) {
        List<T> dataRows = new ArrayList<>();
        try{
            Reader in = new FileReader(strFilePath, StandardCharsets.UTF_8);
            CSVFormat cSVFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(headers)
                    .setSkipHeaderRecord(true)
                    .build();
            Iterable<CSVRecord> records =  CSVParser.parse(in, cSVFormat);
            records.forEach(r-> dataRows.add(transformCSVToTypeRecord(r)));
            in.close();
        } catch (IOException ex){
            logger.error(ex.toString());
        }
        logger.info("Records read from CSV File: " + dataRows.size());
        return dataRows;
    }

    protected abstract T transformCSVToTypeRecord(CSVRecord csvRecord);
}
