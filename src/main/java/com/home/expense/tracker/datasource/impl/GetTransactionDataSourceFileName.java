package com.home.expense.tracker.datasource.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class GetTransactionDataSourceFileName {
    private static final Logger logger = LoggerFactory.getLogger(GetTransactionDataSourceFileName.class);

    public static String getDataSourceFilePath(Environment env){
        ClassLoader classLoader = GetTransactionDataSourceFileName.class.getClassLoader();
        URL resource = classLoader.getResource(env.getProperty("tracker.datasource.file"));
        if (resource == null) {
            logger.error(env.getProperty("tracker.datasource.file") + " Transaction Data csv file not found.");
            return "";
        } else {
            return getFullFilePathFromResource(resource);
        }
    }

    private static String getFullFilePathFromResource(URL resource){
        try{
            File file =  new File(resource.toURI());
            return file.getAbsolutePath();
        }catch (URISyntaxException e){
            logger.error("Transaction Data csv file not found.");
            return "";
        }
    }
}
