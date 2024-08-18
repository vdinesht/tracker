package com.home.expense.tracker.csvfile;

import com.home.expense.tracker.datasource.TransactionDataHeader;
import com.home.expense.tracker.datasource.TransactionDataRow;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class CSVFileWriter {
    private final Logger logger = LoggerFactory.getLogger(CSVFileWriter.class);

    protected boolean saveAllRowsToFile(List<TransactionDataRow> allRows, String filePath){ return saveToFile(false, allRows, filePath);  }

    protected boolean addAllRowsToFile(List<TransactionDataRow> allRows, String filePath) { return saveToFile(true, allRows, filePath); }

    protected boolean saveToFile(boolean append, List<TransactionDataRow> dataRows, String filePath) {
        boolean saveSuccessful = false;
        try{
            FileWriter fileWriter = getFileWriter(append, filePath);
            CSVPrinter printer = CSVFormat.DEFAULT.builder()
                    .setHeader(TransactionDataHeader.class)
                    .build()
                    .print(fileWriter);
            dataRows.forEach(r-> {
                try {
                    printTo(printer, r);
                } catch (IOException e) {
                    logger.error(e.toString());
                }
            });
            printer.close();
            fileWriter.close();
            saveSuccessful = true;
        } catch (IOException ex){
            logger.error(ex.toString());
        }
        logger.info("Records saved to CSV File: " + dataRows.size());
        return saveSuccessful;
    }

    protected FileWriter getFileWriter(boolean append, String filePath) throws IOException {
        return new FileWriter(filePath,append);
    }

    protected abstract void printTo(CSVPrinter printer, TransactionDataRow r) throws IOException;

}
