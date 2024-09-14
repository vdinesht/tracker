package com.home.expense.tracker.entities.statementimport.impl;

import com.home.expense.tracker.entities.statementimport.AccountStatementType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountStatementFinder {
    private final static Logger logger = LoggerFactory.getLogger(AccountStatementFinder.class);
    private final static Map<String, AccountStatementType> mapAccountNumberToStatementType = EnumSet.allOf(AccountStatementType.class).stream().collect(Collectors.toMap(AccountStatementType::getAccountNumber, e->e));

    public static AccountStatementType findStatementType(String accountStatementFile){
        return getAccountStatement(accountStatementFile);
    }

    private static AccountStatementType getAccountStatement(String accountStatementFile){
        Set<AccountStatementType> foundAccountStatements = new HashSet<>();
        try{
            foundAccountStatements = findAvailableAccountStatements(accountStatementFile);
            logger.info("Found Account types in file: " + foundAccountStatements);
            foundAccountStatements.remove(AccountStatementType.Unknown);
        } catch (
                IOException ex){
            logger.error(ex.toString());
        }

        if (foundAccountStatements.size() > 0)
            return foundAccountStatements.stream().toList().get(0);
        else
            return AccountStatementType.Unknown;
    }

    private static Set<AccountStatementType> findAvailableAccountStatements(String accountStatementFile) throws IOException {
        Set<AccountStatementType> foundAccountStatements = new HashSet<>();
        Reader in = new FileReader(accountStatementFile, StandardCharsets.UTF_8);
        CSVFormat cSVFormat = CSVFormat.DEFAULT.builder().build();
        Iterable<CSVRecord> records =  CSVParser.parse(in, cSVFormat);
        for (CSVRecord record : records) {
            mapAccountNumberToStatementType.keySet().forEach(s -> {
                if (record.stream().anyMatch(e->e.contains(s)))
                    foundAccountStatements.add(mapAccountNumberToStatementType.get(s));
            } );
        }
        return foundAccountStatements;
    }
}
