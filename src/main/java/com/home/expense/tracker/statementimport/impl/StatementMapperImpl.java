package com.home.expense.tracker.statementimport.impl;

import com.home.expense.tracker.statementimport.AccountStatementType;
import com.home.expense.tracker.statementimport.StatementMapper;
import com.home.expense.tracker.statementimport.StatementMappingReader;
import com.home.expense.tracker.statementimport.StatementMappingRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StatementMapperImpl implements StatementMapper {

    private final StatementMappingReader statementMappingReader = new StatementMappingCSVReader();

    @Override
    public StatementMappingRow getCreditMatcher(AccountStatementType statementOf, String transactionText){
        return getStatementMappingRow(statementOf, transactionText,statementMappingReader.getAllCreditMappingRows());
    }

    @Override
    public StatementMappingRow getDebitMatcher(AccountStatementType statementOf, String transactionText) {
        return getStatementMappingRow(statementOf, transactionText,statementMappingReader.getAllDebitMappingRows());
    }

    private StatementMappingRow getStatementMappingRow(AccountStatementType statementOf, String transactionText, List<StatementMappingRow> allRows) {
        List<StatementMappingRow> listOfAccountStatement =allRows.stream().filter(e-> statementOf == e.statement()).toList();

        List<StatementMappingRow> matchList = listOfAccountStatement.stream().filter(e-> {  Pattern p = Pattern.compile(e.token());
                                                                                            Matcher m = p.matcher(transactionText);
                                                                                            return m.matches();})
                                                                                .toList();

        return matchList.get(0);
    }
}
