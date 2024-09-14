package com.home.expense.tracker.entities.datasource.impl;

import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.TransactionCurrency;
import com.home.expense.tracker.entities.datasource.TransactionDataHeader;
import com.home.expense.tracker.entities.datasource.TransactionDataRow;
import com.home.expense.tracker.entities.GroupTag;
import com.home.expense.tracker.entities.PrimaryAccount;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

public final class MapCSVRecordToTransactionDataRow {

    private final static Map<String, PrimaryAccount> mapPrimaryAccountToValue = EnumSet.allOf(PrimaryAccount.class).stream().collect(Collectors.toMap(Enum::name, e->e));
    private final static Map<String, SubAccount> mapSubAccountToValue = EnumSet.allOf(SubAccount.class).stream().collect(Collectors.toMap(e->e.getValue(), e->e));
    private final static Map<String, GroupTag> mapGroupTagToValue = EnumSet.allOf(GroupTag.class).stream().collect(Collectors.toMap(Enum::name, e->e));

    private final static Map<String, TransactionCurrency> mapCurrencyToValue = EnumSet.allOf(TransactionCurrency.class).stream().collect(Collectors.toMap(Enum::name, e->e));

    private MapCSVRecordToTransactionDataRow(){}
    public static TransactionDataRow transform(CSVRecord record){
        TransactionDataRowImpl transactionDataRow = new TransactionDataRowImpl(0);

        transactionDataRow.setDate(extractDate(record.get(TransactionDataHeader.Date)));
        transactionDataRow.setAmount(extractAmount(record.get(TransactionDataHeader.Amount)));
        transactionDataRow.setCurrency(extractCurrency(record.get(TransactionDataHeader.Currency)));
        transactionDataRow.setDescription(record.get(TransactionDataHeader.Description));
        transactionDataRow.setDebitAccount(extractDebitAccount(record.get(TransactionDataHeader.DebitAccount)));
        transactionDataRow.setCreditAccount(extractCreditAccount(record.get(TransactionDataHeader.CreditAccount)));
        transactionDataRow.setDebitSubAccount(extractDebitSubAccount(record.get(TransactionDataHeader.DebitSubAccount)));
        transactionDataRow.setCreditSubAccount(extractCreditSubAccount(record.get(TransactionDataHeader.CreditSubAccount)));
        transactionDataRow.setTransType2015(record.get(TransactionDataHeader.Type));
        transactionDataRow.setGroupTag(extractGroupTag(record.get(TransactionDataHeader.GroupTag)));
        transactionDataRow.setGdriveLink(record.get(TransactionDataHeader.GLinkDrive));
        transactionDataRow.id(extractId(record.get(TransactionDataHeader.ID)));

        return transactionDataRow;
    }

    private static LocalDate extractDate(String dateString){
        return LocalDate.parse(dateString);
    }

    private static double extractAmount(String amountString){
        return Double.parseDouble(String.format("%.2f", Double.parseDouble(amountString.replace(",", ""))));
    }

    private static SubAccount extractDebitSubAccount(String debitAcc){
        return mapSubAccountToValue.get(debitAcc);
    }
    private static SubAccount extractCreditSubAccount(String creditAcc){
        return mapSubAccountToValue.get(creditAcc);
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

    private static TransactionCurrency extractCurrency(String currency){
        return mapCurrencyToValue.get(currency);
    }
    private static int extractId(String IdString){
        return Integer.parseInt(IdString);
    }

}

