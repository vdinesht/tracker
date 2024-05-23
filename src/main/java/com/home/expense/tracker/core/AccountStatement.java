package com.home.expense.tracker.core;

public enum AccountStatement {

    Bank_ICICI_Ngl_Joint("ICICI Ngl Joint"),
    Bank_ICICI_Thoraipakkam_Dinesh("ICICI Thoraipakkam - Dinesh"),
    Bank_ICICI_Thoraipakkam_Sebina("ICICI Thoraipakkam - Sebina"),
    CC_SBI_Elite_Dinesh("SBI Elite - Dinesh");
    private final String value;

    AccountStatement(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
