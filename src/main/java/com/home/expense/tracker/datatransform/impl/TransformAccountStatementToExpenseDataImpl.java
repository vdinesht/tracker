package com.home.expense.tracker.datatransform.impl;

import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.datasource.impl.TransactionDataRowImpl;
import com.home.expense.tracker.datatransform.TransformAccountStatementToExpenseData;
import com.home.expense.tracker.imports.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransformAccountStatementToExpenseDataImpl implements TransformAccountStatementToExpenseData {
    private final Logger logger = LoggerFactory.getLogger(TransformAccountStatementToExpenseDataImpl.class);

    @Autowired
    private StatementMapper statementMapper;

    private List<TransactionDataRow> convertToListOfTransactionDataRows(AccountStatement statement) {
        List<TransactionDataRow> rowList = new ArrayList<>();
        statement.getAllRows().forEach(e-> rowList.add(mapBankStatementToTransactionDataRow(statement.statementName(), e)));
        return  rowList;
    }

    private TransactionDataRow mapBankStatementToTransactionDataRow(AccountStatementName statementName, AccountStatementRow statementRow) {
        if (statementRow.transactionType() == StatementTransactionType.Debit)
            return getTransactionDataRow(statementMapper.getDebitMatcher(statementName, statementRow.transactionDescription()), statementRow);

        else
            return getTransactionDataRow(statementMapper.getCreditMatcher(statementName, statementRow.transactionDescription()), statementRow);
    }

    private static TransactionDataRow getTransactionDataRow(StatementMappingRow statementMappingRow, AccountStatementRow statementRow) {
        TransactionDataRowImpl transactionDataRow = new TransactionDataRowImpl();

        transactionDataRow.setDate(statementRow.transactionDate());
        transactionDataRow.setCurrency(statementRow.currency());
        transactionDataRow.setAmount(statementRow.transactionAmount());
        transactionDataRow.setDescription(statementRow.transactionDescription());
        transactionDataRow.setDebitAccount(statementMappingRow.debitAccount());
        transactionDataRow.setCreditAccount(statementMappingRow.creditAccount());
        transactionDataRow.setDebitSubAccount(statementMappingRow.debitSubAccount());
        transactionDataRow.setCreditSubAccount(statementMappingRow.creditSubAccount());

        return transactionDataRow;
    }

    @Override
    public List<TransactionDataRow> transform(AccountStatement statement) {
        return convertToListOfTransactionDataRows(statement);
    }

}
