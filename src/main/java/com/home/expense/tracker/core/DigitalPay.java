package com.home.expense.tracker.core;

//Digital Payment Accounts
public enum DigitalPay {
    Housing_Expenses("Housing Expenses"),
    Travel_Expense("Travel Expense"),
    Food_Expenses("Food Expenses"),
    Clothing_Expenses("Clothing Expenses"),
    Education_Expenses("Education Expenses"),
    Other_Expense("Other Expense"),
    Personal_Loan("Personal Loan"),
    Vendor_Bank("Vendor Bank"),
    Caterpillar_Inc("Caterpillar Inc"),
    Caterpillar_India("Caterpillar India"),
    Tenant("Tenant"),
    SBI("SBI"),
    ICICI("ICICI"),
    Medical_Expense("Medical Expense"),
    CEFCU("CEFCU"),
    Other_Bank("Other Bank"),
    Other_Employer("Other Employer");
    private final String value;

    DigitalPay(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
