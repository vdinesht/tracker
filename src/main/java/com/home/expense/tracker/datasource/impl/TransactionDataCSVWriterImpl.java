package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.datasource.TransactionDataHeader;
import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.datasource.TransactionDataWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionDataCSVWriterImpl implements TransactionDataWriter {
    private final Logger logger = LoggerFactory.getLogger(TransactionDataCSVWriterImpl.class);
    @Autowired
    private Environment env;

    @Override
    public boolean saveAll(List<TransactionDataRow> allRows){ return saveToFile(false, allRows);  }

    @Override
    public boolean addAll(List<TransactionDataRow> allRows) { return saveToFile(true, allRows); }

    private boolean saveToFile(boolean append, List<TransactionDataRow> dataRows) {
        boolean saveSuccessful = false;
        try{
            FileWriter fileWriter = getFileWriter(append);
            CSVPrinter printer = CSVFormat.DEFAULT.builder()
                                            .setHeader(TransactionDataHeader.class)
                                            .build()
                                            .print(fileWriter);
            dataRows.forEach(r->printTo(printer, r));
            printer.close();
            fileWriter.close();
            saveSuccessful = true;
        } catch (IOException ex){
            logger.error(ex.toString());
        }
        logger.info("Records saved to CSV File: " + dataRows.size());
        return saveSuccessful;
    }

    private FileWriter getFileWriter(boolean append) throws IOException {
        return new FileWriter(Objects.requireNonNull(env.getProperty("tracker.datasource.file")),append);
    }

    private void printTo(CSVPrinter printer, TransactionDataRow r)  {
        try {
            printer.print(r.date());
            printer.print(r.amount());
            printer.print(r.currency());
            printer.print(r.description());
            printer.print(r.debitAccount());
            printer.print(r.creditAccount());
            printer.print(r.debitSubAccount());
            printer.print(r.creditSubAccount());
            printer.print(r.transType2015());
            printer.print(r.groupTag());
            printer.print(r.gdriveLink());
            printer.print(r.id());
            printer.println();
        }
        catch (IOException ioException){
            logger.error(ioException.toString());
        }
    }
}
