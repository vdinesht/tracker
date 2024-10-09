package com.home.expense.tracker.entities.transaction.impl;

import com.home.expense.tracker.boundryobjects.reports.csvfile.CSVFileWriter;
import com.home.expense.tracker.entities.transaction.TransactionDataHeader;
import com.home.expense.tracker.entities.transaction.TransactionDataRow;
import com.home.expense.tracker.entities.transaction.TransactionDataWriter;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TransactionDataCSVWriterImpl extends CSVFileWriter<TransactionDataRow> implements TransactionDataWriter  {

    private final String filePath;

    public TransactionDataCSVWriterImpl(String filePath) {
        this.filePath = filePath;
    }

    protected void printTo(CSVPrinter printer, TransactionDataRow r) throws IOException {
        printer.print(r.date());
        String strAmount = String.format("%,.2f", r.amount());
        printer.print(strAmount);
        printer.print(r.currency());
        printer.print(r.description());
        printer.print(r.debitAccount());
        printer.print(r.creditAccount());
        printer.print(r.debitSubAccount().getValue());
        printer.print(r.creditSubAccount().getValue());
        printer.print(r.transType2015());
        printer.print(r.groupTag());
        printer.print(r.gdriveLink());
        printer.print(r.id());
        printer.println();
    }

    @Override
    public boolean saveAll(List<TransactionDataRow> allRows) {
        return saveAllRowsToFile(Arrays.stream(TransactionDataHeader.values()).map(Enum::name).toArray(String[]::new),allRows, this.filePath);
    }

    @Override
    public boolean addAll(List<TransactionDataRow> allRows) {
        return addAllRowsToFile(Arrays.stream(TransactionDataHeader.values()).map(Enum::name).toArray(String[]::new), allRows, this.filePath);
    }

}
