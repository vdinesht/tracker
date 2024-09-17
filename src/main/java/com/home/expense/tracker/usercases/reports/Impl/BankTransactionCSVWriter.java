package com.home.expense.tracker.usercases.reports.Impl;

import com.home.expense.tracker.boundryobjects.reports.csvfile.CSVFileWriter;
import com.home.expense.tracker.usercases.reports.BankTransactionDataHeader;
import com.home.expense.tracker.usercases.reports.BankTransactionDataRow;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BankTransactionCSVWriter extends CSVFileWriter<BankTransactionDataRow> {

    private final String filePath;

    public BankTransactionCSVWriter(String filePath) {
        this.filePath = filePath;
    }
    @Override
    protected void printTo(CSVPrinter printer, BankTransactionDataRow row) throws IOException {
        printer.print(row.date());
        String strAmount = String.format("%,.2f", row.amount());
        printer.print(strAmount);
        printer.print(row.currency());
        printer.print(row.description());
        printer.print(row.debitAccount());
        printer.print(row.creditAccount());
        printer.print(row.debitSubAccount().getValue());
        printer.print(row.creditSubAccount().getValue());
        printer.print(row.groupTag());
        printer.print(row.gdriveLink());
        printer.print(row.id());
        String strBalance = String.format("%,.2f", row.balance());
        printer.print(strBalance);
        printer.println();
    }

    public boolean saveAll(List<BankTransactionDataRow> allRows) {
        return saveAllRowsToFile(Arrays.stream(BankTransactionDataHeader.values()).map(Enum::name).toArray(String[]::new),allRows, this.filePath);
    }

    public boolean addAll(List<BankTransactionDataRow> allRows) {
        return addAllRowsToFile(Arrays.stream(BankTransactionDataHeader.values()).map(Enum::name).toArray(String[]::new), allRows, this.filePath);
    }


}
