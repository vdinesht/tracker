package com.home.expense.tracker.entities.statementimport.impl;

import com.home.expense.tracker.entities.statementimport.StatementMappingReader;
import com.home.expense.tracker.entities.statementimport.AccountStatementType;
import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.statementimport.StatementMappingRow;
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
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatementMappingCSVReader implements StatementMappingReader {

    private final Logger logger = LoggerFactory.getLogger(StatementMappingCSVReader.class);
    private final static Map<String, PrimaryAccount> mapPrimaryAccountToValue = EnumSet.allOf(PrimaryAccount.class).stream().collect(Collectors.toMap(Enum::name, e->e));
    private final static Map<String, SubAccount> mapSubAccountToValue = EnumSet.allOf(SubAccount.class).stream().collect(Collectors.toMap(SubAccount::getValue, e->e));

    private final static Map<String, AccountStatementType> mapAccountStatementToValue = EnumSet.allOf(AccountStatementType.class).stream().collect(Collectors.toMap(Enum::name, e->e));

    private List<StatementMappingRow> creditMappingList = new ArrayList<>();
    private List<StatementMappingRow> debitMappingList = new ArrayList<>();

    private List<StatementMappingRow> fillDataRows(String mappingFilePath) {
        List<StatementMappingRow> dataRowList = new ArrayList<>();
        try{
            Reader in = new FileReader(mappingFilePath, StandardCharsets.UTF_8);
            CSVFormat cSVFormat = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(false).build();
            Iterable<CSVRecord> records =  CSVParser.parse(in, cSVFormat);
            for (CSVRecord record : records) {
                if (!record.values()[0].isEmpty())
                    dataRowList.add(MapCSVRecordToStatementmappingRow(record));
            }
            in.close();
        } catch (IOException ex){
            logger.error(ex.toString());
        }
        logger.info("Records read from ICICI Bank Statement CSV File: " + dataRowList.size());
        return dataRowList;
    }

    private StatementMappingRow MapCSVRecordToStatementmappingRow(CSVRecord record){
        return new StatementMappingRowImpl(mapAccountStatementToValue.get(record.values()[0]),
                                                                                record.values()[1],
                                                                                mapPrimaryAccountToValue.get(record.values()[2]),
                                                                                mapPrimaryAccountToValue.get(record.values()[3]),
                                                                                mapSubAccountToValue.get(record.values()[4]),
                                                                                mapSubAccountToValue.get(record.values()[5]));
    }

    @Override
    public List<StatementMappingRow> getAllDebitMappingRows() {
        String debitMappingFilePath = "C:\\Temp\\ExpenseTracker\\BankStatement\\StatementDebitMapping.csv";
        if (debitMappingList.isEmpty())
            debitMappingList = fillDataRows(debitMappingFilePath);

        return debitMappingList;
    }

    @Override
    public List<StatementMappingRow> getAllCreditMappingRows() {
        String creditMappingFilePath = "C:\\Temp\\ExpenseTracker\\BankStatement\\StatementCreditMapping.csv";
        if (creditMappingList.isEmpty())
            creditMappingList = fillDataRows(creditMappingFilePath);

        return creditMappingList;
    }

}
