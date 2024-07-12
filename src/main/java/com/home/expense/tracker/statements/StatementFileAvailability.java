package com.home.expense.tracker.statements;

import java.util.List;

public interface StatementFileAvailability {
    boolean isAvailable();
    String getNextAvailableFilePath();
 }
