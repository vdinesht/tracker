package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.datasource.DataRow2024;
import com.home.expense.tracker.datasource.Expense2024Reader;
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
public class Expense2024CSVReaderImpl implements Expense2024Reader {
    private final Logger logger = LoggerFactory.getLogger(Expense2024CSVReaderImpl.class);
    private final String expenseTracker2024File = "C:\\Temp\\ExpenseTracker\\OurHomeExpense2024UTF8.csv";

    private List<DataRow2024> dataRow2024List = new ArrayList<>();

    @Override
    public List<DataRow2024> getAllRows()  {
        if (dataRow2024List.isEmpty())
            return fillDataRows();
        else
            return dataRow2024List;
    }

    private List<DataRow2024> fillDataRows() {
        try{
            Reader in = new FileReader(expenseTracker2024File, StandardCharsets.UTF_8);
            CSVFormat cSVFormat = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(false).build();
            Iterable<CSVRecord> records =  CSVParser.parse(in, cSVFormat);
            for (CSVRecord record : records) {
                dataRow2024List.add(MapCSVRecordToDigitalRow2024.transform(record));
            }
        }
        catch (FileNotFoundException ex){
            logger.error(ex.toString());
        }
        catch (IOException ioException) {
            logger.error(ioException.toString());
        }
        logger.info("Records read from CSV File: " + dataRow2024List.size());
        return dataRow2024List;
    }
}
