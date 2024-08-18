package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.csvfile.CSVFileWriter;
import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.datasource.TransactionDataWriter;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.util.List;

public class TransactionDataCSVWriterImpl extends CSVFileWriter implements TransactionDataWriter  {

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
        return saveAllRowsToFile(allRows, this.filePath);
    }

    @Override
    public boolean addAll(List<TransactionDataRow> allRows) {
        return addAllRowsToFile(allRows, this.filePath);
    }

}
