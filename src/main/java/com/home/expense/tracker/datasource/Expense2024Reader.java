package com.home.expense.tracker.datasource;

import java.io.FileNotFoundException;
import java.util.List;

public interface Expense2024Reader {
    List<DataRow2024> getAllRows();
}
