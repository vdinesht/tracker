package com.home.expense.tracker.entities.csvfile;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class CSVFileWriter <T> {
    private final Logger logger = LoggerFactory.getLogger(CSVFileWriter.class);

    protected boolean saveAllRowsToFile(String headers[], List<T> allRows, String filePath){ return saveToFile(false, headers, allRows, filePath);  }

    protected boolean addAllRowsToFile(String headers[], List<T> allRows, String filePath) { return saveToFile(true, headers, allRows, filePath); }

    protected boolean saveToFile(boolean append, String headers[], List<T> dataRows, String filePath) {
        boolean saveSuccessful = false;

        try{
            FileWriter fileWriter = getFileWriter(append, filePath);
            CSVPrinter printer = CSVFormat.DEFAULT.builder()
                    .setHeader(headers)
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

    protected abstract void printTo(CSVPrinter printer, T row) throws IOException;

}
