package com.home.expense.tracker.statements.impl;

import com.home.expense.tracker.statements.StatementFileAvailability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StatementFileAvailabilityImpl implements StatementFileAvailability {
    private final Logger logger = LoggerFactory.getLogger(StatementFileAvailabilityImpl.class);

    @Autowired
    private Environment env;

    @Override
    public boolean isAvailable() {
        return checkIfCSVFileIsfound();
    }

    @Override
    public String getNextAvailableFilePath() {
        String filePath = "";
        if (checkIfCSVFileIsfound())
            filePath = getAllCSVFiles().get(0);
        return filePath;
    }

    private boolean checkIfCSVFileIsfound() {
        if (getAllCSVFiles().size() > 0 )
            return true;
        else
            return false;
    }

    private List<String> getAllCSVFiles() {
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

}
