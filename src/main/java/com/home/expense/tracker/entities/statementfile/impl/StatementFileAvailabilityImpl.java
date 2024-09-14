package com.home.expense.tracker.entities.statementfile.impl;

import com.home.expense.tracker.entities.statementfile.StatementFileAvailability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class StatementFileAvailabilityImpl implements StatementFileAvailability {
    private final Logger logger = LoggerFactory.getLogger(StatementFileAvailabilityImpl.class);

    @Autowired
    private Environment env;

    @Override
    public String getNextAvailableFilePath() {
        List<String> availableFiles = getAllCSVFilesNotProcessed();
        if (availableFiles.size() > 0)
            return availableFiles.get(0);
        else
            return "";
    }
    private List<String> getAllCSVFilesNotProcessed(){
        Set<String> allFilesInFolder = new HashSet<>(getAllCSVFilesInSourceFolder());
        Set<String> allProcessedFiles = new HashSet<>(getAllCSVFileAlreadyProcessed());

        allFilesInFolder.removeAll(allProcessedFiles);
        return new ArrayList<>(allFilesInFolder);
    }
    private List<String> getAllCSVFileAlreadyProcessed()  {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(env.getProperty("tracker.statements.processed.statusfile"))));
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return lines;
    }

    private List<String> getAllCSVFilesInSourceFolder() {
        List<String> csvFilePaths = new ArrayList<>();
        try{
            logger.info("Source Folder Path: " + env.getProperty("tracker.statements.source.folder"));
            File[] files = new File(Objects.requireNonNull(env.getProperty("tracker.statements.source.folder"))).listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    if (file.getName().contains(".csv"))
                        csvFilePaths.add(file.getPath());
                }
            }
        }
        catch (NullPointerException exception){
            logger.error(exception.toString());
        }

        return csvFilePaths;
    }

    private String getFileNameOnly(String fullFilePath){
        File file = new File(fullFilePath);
        return file.getName();
    }
}
