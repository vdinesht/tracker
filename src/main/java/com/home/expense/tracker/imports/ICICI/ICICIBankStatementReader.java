package com.home.expense.tracker.imports.ICICI;

import java.util.List;

public interface ICICIBankStatementReader {
    List<ICICIBankStatementRow> getAllRows();

    List<ICICIBankStatementRow> getAllCreditRows();

    List<ICICIBankStatementRow> getAllDebitRows();


}
