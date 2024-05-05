package com.home.expense.tracker.datasource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class Expense2024CSVReaderImplTest {
    @Autowired
    Expense2024Reader expense2024Reader;
    @Test
    void testCSVReading(){
        List<DataRow2024> dataRows = expense2024Reader.getAllRows();
        System.out.println(dataRows.get(12503));
        assertTrue(dataRows.size()>0);

    }
}
