package com.home.expense.tracker.imports.ICICI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StatementCSVReaderTest {

    @Autowired
    StatementReader statementReader;

    private int count = 0;
    @Test
    void testCSVReader(){
        statementReader.getAllRows().forEach(e-> System.out.println(parseTransactionRemarks(e.transactionRemarks())));
        assertTrue(statementReader.getAllRows().size()>0);

        System.out.println("Identified count: " + count);
    }


    private String parseTransactionRemarks(String raw){
        String[] iciciTokens = {"UPI/", "ACH/" , "MMT/", "BIL/", "NEFT", "CMS/", "DMC/", "ATM/", "Int.Pd", "VSI/", "GIB/", "DCardfee", "VIN/", "NFS/", "DTK/", "CLG/"};

        if (Arrays.stream(iciciTokens).anyMatch(raw::contains)) {
            ++count;
            return "";
        }
        else  return raw;
    }
}
