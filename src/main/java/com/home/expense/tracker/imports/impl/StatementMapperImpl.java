package com.home.expense.tracker.imports.impl;

import com.home.expense.tracker.imports.AccountStatementName;
import com.home.expense.tracker.imports.StatementMapper;
import com.home.expense.tracker.imports.StatementMappingReader;
import com.home.expense.tracker.imports.StatementMappingRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class StatementMapperImpl implements StatementMapper {

    private final StatementMappingReader statementMappingReader = new StatementMappingCSVReader();

    @Override
    public StatementMappingRow getCreditMatcher(AccountStatementName statementOf, String transactionText){
        return getStatementMappingRow(statementOf, transactionText,statementMappingReader.getAllCreditMappingRows());
    }

    @Override
    public StatementMappingRow getDebitMatcher(AccountStatementName statementOf, String transactionText) {
        return getStatementMappingRow(statementOf, transactionText,statementMappingReader.getAllDebitMappingRows());
    }

    private StatementMappingRow getStatementMappingRow(AccountStatementName statementOf, String transactionText, List<StatementMappingRow> allRows) {
        List<StatementMappingRow> listOfAccountStatement =allRows.stream().filter(e-> statementOf == e.statement()).collect(Collectors.toList());

        List<StatementMappingRow> matchList = listOfAccountStatement.stream().filter(e-> {  Pattern p = Pattern.compile(e.token());
                                                                                            Matcher m = p.matcher(transactionText);
                                                                                            return m.matches();})
                                                                                .collect(Collectors.toList());

        return matchList.get(0);
    }
}
