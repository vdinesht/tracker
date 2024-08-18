package com.home.expense.tracker.statementfile.impl;

import com.home.expense.tracker.statementfile.StatementFileTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class StatementFileTransferImpl implements StatementFileTransfer {
    private final Logger logger = LoggerFactory.getLogger(StatementFileTransferImpl.class);

    @Autowired
    private Environment env;

    @Override
    public boolean completedStatementFileProcessing(String fullFilePath) {
        try {
            Writer output;
            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Objects.requireNonNull(env.getProperty("tracker.statements.processed.statusfile")), true), StandardCharsets.UTF_8));
            output.append(fullFilePath);

            output.close();
            return true;
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            return false;
        }
    }

}
