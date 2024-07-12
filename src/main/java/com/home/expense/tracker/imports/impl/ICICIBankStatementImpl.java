package com.home.expense.tracker.imports.impl;

import com.home.expense.tracker.imports.AccountStatement;
import com.home.expense.tracker.imports.AccountStatementName;
import com.home.expense.tracker.imports.AccountStatementRow;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ICICIBankStatementImpl implements AccountStatement {
    private final Logger logger = LoggerFactory.getLogger(ICICIBankStatementImpl.class);
    private final String statementFile = "C:\\Temp\\ExpenseTracker\\BankStatement\\OpTransactionHistory1JanTo31Dec2023UTF8.csv";

    private final List<AccountStatementRow> dataRowList = new ArrayList<>();

    @Override
    public AccountStatementName statementName() {
        return AccountStatementName.BankICICIThoraipakkamDinesh;
    }

    @Override
    public List<AccountStatementRow> getAllRows()  {
        if (dataRowList.isEmpty())
            return fillDataRows();
        else
            return dataRowList;
    }

    private List<AccountStatementRow> fillDataRows() {
        try{
            Reader in = new FileReader(statementFile, StandardCharsets.UTF_8);
            CSVFormat cSVFormat = CSVFormat.DEFAULT.builder().build();
            Iterable<CSVRecord> records =  CSVParser.parse(in, cSVFormat);
            for (CSVRecord record : records) {
                if (!record.values()[1].isEmpty()) {
                    try{
                        if (Integer.parseInt(record.values()[1]) > 0){
                            dataRowList.add(transformCSVRecordToStatementRow(record));
                        }
                    }
                    catch (NumberFormatException ex){
                        logger.info("Skipping Row : " + record);
                    }
                }
            }
        } catch (IOException ex){
            logger.error(ex.toString());
        }
        logger.info("Records read from ICICI Bank Statement CSV File: " + dataRowList.size());
        return dataRowList;
    }

    private AccountStatementRow transformCSVRecordToStatementRow(CSVRecord record){
        ICICIStatementRowImpl row = new ICICIStatementRowImpl();
        row.setRow(Integer.parseInt(record.values()[1]));
        row.setValueDate(convertStringToDate(record.values()[2]));
        row.setTransactionDate(convertStringToDate(record.values()[3]));
        row.setChequeNumber(record.values()[4]);
        row.setTransactionRemarks(record.values()[5].trim());
        row.setWithdrawAmount(convertStringToDouble(record.values()[6]));
        row.setDepositAmount(convertStringToDouble(record.values()[7]));
        row.setBalance(convertStringToDouble(record.values()[8]));
        return row;
    }

    private LocalDate convertStringToDate(String strDate){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            localDate = LocalDate.parse(strDate, dateTimeFormatter);
        } catch (DateTimeParseException ex){
            logger.error("Unable to parse date : " + strDate);
        }
        return localDate;
    }

    private double convertStringToDouble(String strValue){
        double value = 0D;
        try {
            value = Double.parseDouble(strValue);
        } catch (NumberFormatException ex) {
            logger.error("Unable to parse number : "+ strValue);
        }
        return value;
    }

}