package com.home.expense.tracker.imports.ICICI;

import java.time.LocalDate;

public interface StatementRow {
    int sno();
    LocalDate valueDate();
    LocalDate transactionDate();
    String chequeNo();
    String transactionRemarks();
    Double withdrawAmount();
    Double depositAmount();
    Double balance();
}
