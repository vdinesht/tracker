package com.home.expense.tracker.datasource;

public enum CashPay {
    Housing_Expenses("Housing Expenses"),
    Travel_Expense("Travel Expense"),
    Food_Expenses("Food Expenses"),
    Clothing_Expenses("Clothing Expenses"),
    Education_Expenses("Education Expenses"),
    Other_Expense("Other Expense"),
    Personal_Loan("Personal Loan"),
    Vendor_Cash("Vendor Cash"),
    Medical_Expense("Medical Expense"),
    Tenant("Tenant");
    private final String value;

    CashPay(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
