package com.home.expense.tracker.core;

public enum BankAccount {
    CEFCU_Checking_Dinesh("CEFCU Checking - Dinesh"),
    CEFCU_Checking_Sebina("CEFCU Checking - Sebina"),
    CEFCU_Saving_Dinesh("CEFCU Saving - Dinesh"),
    CEFCU_Saving_Sebina("CEFCU Saving - Sebina"),
    ICICI_Ngl_Joint("ICICI Ngl Joint"),
    ICICI_Thoraipakkam_Dinesh("ICICI Thoraipakkam - Dinesh"),
    ICICI_Thoraipakkam_Sebina("ICICI Thoraipakkam - Sebina"),
    SBI_Kulithurai_NRE("SBI Kulithurai NRE"),
    ICICI_Ngl_NRE("ICICI Ngl NRE"),
    Canara_Bank_Guindy("Canara Bank Guindy"),
    SBI_Perungudi_Dinesh("SBI Perungudi - Dinesh"),
    SBI_Perungudi_Sebina("SBI Perungudi - Sebina"),
    SBI_Green_Acres_Home_Loan("SBI Green Acres Home Loan"),
    HSBC_Home_Loan("HSBC Home Loan"),
    India_Bulls_Home_Loan("India Bulls Home Loan"),
    Canara_Bank_Adyar("Canara Bank Adyar"),
    HSBC_Perungudi_Dinesh("HSBC Perungudi - Dinesh"),
    HSBC_Perungudi_Netanya("HSBC Perungudi - Netanya"),
    HSBC_Sebina("HSBC - Sebina");

    private final String value;

    BankAccount(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
