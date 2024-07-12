package com.home.expense.tracker.statements.impl;

import com.home.expense.tracker.statements.StatementFileTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StatementFileTransferImpl implements StatementFileTransfer {
    private final Logger logger = LoggerFactory.getLogger(StatementFileTransferImpl.class);

    @Autowired
    private Environment env;
    @Override
    public boolean moveStatementFile(String fullFilePath) {
        boolean fileMoved = true;
        try {
            logger.info("Destination Folder Path: " + env.getProperty("tracker.statements.processed.folder"));

            String copyFilePath = env.getProperty("tracker.statements.processed.folder") + "\\" + getFileNameOnly(fullFilePath);
            logger.info("Copying file to: " + copyFilePath);
            Files.move(Paths.get(fullFilePath), Paths.get(copyFilePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            fileMoved = false;
            logger.error(e.toString());
        }

        return fileMoved;
    }

    private String getFileNameOnly(String fullFilePath){
        File file = new File(fullFilePath);
        return file.getName();
    }
}
