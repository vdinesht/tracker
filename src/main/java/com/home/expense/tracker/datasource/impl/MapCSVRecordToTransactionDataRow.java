package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.datasource.TransactionDataHeader;
import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.core.GroupTag;
import com.home.expense.tracker.core.PrimaryAccount;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

public final class MapCSVRecordToTransactionDataRow {

    private final static Map<String, PrimaryAccount> mapPrimaryAccountToValue = EnumSet.allOf(PrimaryAccount.class).stream().collect(Collectors.toMap(e->e.name(), e->e));
    private final static Map<String, GroupTag> mapGroupTagToValue = EnumSet.allOf(GroupTag.class).stream().collect(Collectors.toMap(e->e.name(), e->e));

    private MapCSVRecordToTransactionDataRow(){}
    public static TransactionDataRow transform(CSVRecord record){
        TransactionDataRowImpl transactionDataRow = new TransactionDataRowImpl();

        transactionDataRow.setDate(extractDate(record.get(TransactionDataHeader.Date)));
        transactionDataRow.setAmount(extractAmount(record.get(TransactionDataHeader.Amount)));
        transactionDataRow.setCurrency(record.get(TransactionDataHeader.Currency));
        transactionDataRow.setDescription(record.get(TransactionDataHeader.Description));
        transactionDataRow.setDebitAccount(extractDebitAccount(record.get(TransactionDataHeader.DebitAccount)));
        transactionDataRow.setCreditAccount(extractCreditAccount(record.get(TransactionDataHeader.CreditAccount)));
        transactionDataRow.setDebitSubAccount(record.get(TransactionDataHeader.DebitSubAccount));
        transactionDataRow.setCreditSubAccount(record.get(TransactionDataHeader.CreditSubAccount));
        transactionDataRow.setTransType2015(record.get(TransactionDataHeader.Type));
        transactionDataRow.setGroupTag(extractGroupTag(record.get(TransactionDataHeader.GroupTag)));
        transactionDataRow.setGdriveLink(record.get(TransactionDataHeader.GLinkDrive));
        transactionDataRow.setId(extractId(record.get(TransactionDataHeader.ID)));

        return transactionDataRow;
    }

    private static LocalDate extractDate(String dateString){
        return LocalDate.parse(dateString);
    }

    private static double extractAmount(String amountString){
        return Double.parseDouble(amountString);
    }

    private static PrimaryAccount extractDebitAccount(String debitAcc){
        return mapPrimaryAccountToValue.get(debitAcc);
    }
    private static PrimaryAccount extractCreditAccount(String creditAcc){
        return mapPrimaryAccountToValue.get(creditAcc);
    }
    private static GroupTag extractGroupTag(String groupTagString){
        return mapGroupTagToValue.get(groupTagString);
    }
    private static int extractId(String IdString){
        return Integer.parseInt(IdString);
    }

}

