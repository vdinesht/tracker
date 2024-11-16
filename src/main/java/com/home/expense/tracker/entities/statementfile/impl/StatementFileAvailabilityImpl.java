package com.home.expense.tracker.entities.statementfile.impl;

import com.home.expense.tracker.entities.statementfile.StatementFileAvailability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

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

        return getAllCSVFilesInSourceFolder();
    }

    private List<String> getAllCSVFilesInSourceFolder() {
        List<String> csvFilePaths = new ArrayList<>();
        try {
            Path resourcePath = Paths.get(Objects.requireNonNull(StatementFileAvailabilityImpl.class.getResource("/")).toURI());
            logger.info("Source Folder Path: " + resourcePath);

            try (Stream<Path> paths = Files.walk(resourcePath)) {
                paths.filter(Files::isRegularFile)
                        .forEach(path-> checkForCsvFileAndAdd(csvFilePaths, String.valueOf(path.getFileName())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            logger.error(e.toString());
        }

        return csvFilePaths;
    }

    private static void checkForCsvFileAndAdd(List<String> csvFilePaths, String fileName) {
        if (fileName.contains(".csv"))
            csvFilePaths.add(fileName);
    }

}
