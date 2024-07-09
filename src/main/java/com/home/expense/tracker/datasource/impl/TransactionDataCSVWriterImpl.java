package com.home.expense.tracker.datasource.impl;

import com.home.expense.tracker.datasource.TransactionDataHeader;
import com.home.expense.tracker.datasource.TransactionDataRow;
import com.home.expense.tracker.datasource.TransactionDataWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class TransactionDataCSVWriterImpl implements TransactionDataWriter {
    private final Logger logger = LoggerFactory.getLogger(TransactionDataCSVWriterImpl.class);
    private final String transactionsDataFile = "C:\\Temp\\ExpenseTracker\\OurHomeTransactionsDataUTF8V1.csv";

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
        }
        catch (FileNotFoundException ex){
            logger.error(ex.toString());
        }
        catch (IOException ioException) {
            logger.error(ioException.toString());
        }
        logger.info("Records saved to CSV File: " + dataRows.size());
        return saveSuccessful;
    }

    private FileWriter getFileWriter(boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(transactionsDataFile,append);
        return fileWriter;
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
