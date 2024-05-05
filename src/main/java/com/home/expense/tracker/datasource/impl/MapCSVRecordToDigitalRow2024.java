package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.datasource.DataRow2024;
import com.home.expense.tracker.datasource.GroupTag;
import com.home.expense.tracker.datasource.PrimaryAccount;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

public final class MapCSVRecordToDigitalRow2024 {

    private final static Map<String, PrimaryAccount> mapPrimaryAccountToValue = EnumSet.allOf(PrimaryAccount.class).stream().collect(Collectors.toMap(e->e.getValue(), e->e));
    private final static Map<String, GroupTag> mapGroupTagToValue = EnumSet.allOf(GroupTag.class).stream().collect(Collectors.toMap(e->e.getValue(), e->e));

    private MapCSVRecordToDigitalRow2024(){}
    public static DataRow2024 transform(CSVRecord record){
        DataRow2024Impl dataRow2024 = new DataRow2024Impl();

        dataRow2024.setDate(extractDate(record.values()[0]));
        dataRow2024.setAmount(extractAmount(record.get("Amount")));
        dataRow2024.setCurrency(record.get("Currency"));
        dataRow2024.setDescription(record.get("Description"));
        dataRow2024.setDebitAccount(extractDebitAccount(record.get("DebitAccount")));
        dataRow2024.setCreditAccount(extractCreditAccount(record.get("CreditAccount")));
        dataRow2024.setDebitSubAccount(record.get("DebitSubAccount"));
        dataRow2024.setCreditSubAccount(record.get("CreditSubAccount"));
        dataRow2024.setTransType2015(record.get("Type"));
        dataRow2024.setGroupTag(extractGroupTag(record.get("GroupTag")));
        dataRow2024.setGdriveLink(record.get("GLinkDrive"));
        dataRow2024.setId(extractId(record.get("ID")));

        return dataRow2024;
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

