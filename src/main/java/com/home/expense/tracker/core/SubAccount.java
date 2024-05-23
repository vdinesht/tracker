package com.home.expense.tracker.core;

public enum SubAccount {

    Bank_CEFCU_Checking_Dinesh("CEFCU Checking - Dinesh"),
    Bank_CEFCU_Checking_Sebina("CEFCU Checking - Sebina"),
    Bank_CEFCU_Saving_Dinesh("CEFCU Saving - Dinesh"),
    Bank_CEFCU_Saving_Sebina("CEFCU Saving - Sebina"),
    Bank_ICICI_Ngl_Joint("ICICI Ngl Joint"),
    Bank_ICICI_Thoraipakkam_Dinesh("ICICI Thoraipakkam - Dinesh"),
    Bank_ICICI_Thoraipakkam_Sebina("ICICI Thoraipakkam - Sebina"),
    Bank_SBI_Kulithurai_NRE("SBI Kulithurai NRE"),
    Bank_ICICI_Ngl_NRE("ICICI Ngl NRE"),
    Bank_Canara_Bank_Guindy("Canara Bank Guindy"),
    Bank_SBI_Perungudi_Dinesh("SBI Perungudi - Dinesh"),
    Bank_SBI_Perungudi_Sebina("SBI Perungudi - Sebina"),
    Bank_SBI_Green_Acres_Home_Loan("SBI Green Acres Home Loan"),
    Bank_HSBC_Home_Loan("HSBC Home Loan"),
    Bank_India_Bulls_Home_Loan("India Bulls Home Loan"),
    Bank_Canara_Bank_Adyar("Canara Bank Adyar"),
    Bank_HSBC_Perungudi_Dinesh("HSBC Perungudi - Dinesh"),
    Bank_HSBC_Perungudi_Netanya("HSBC Perungudi - Netanya"),
    CC_Gift_Card("Gift Card"),
    CC_Shell_Charge_Card("Shell Charge Card"),
    CC_CEFCU_Dinesh("CEFCU - Dinesh"),
    CC_Sears_Master_Card("Sears Master Card"),
    CC_Sams_Club_Discover_Card("Sams Club Discover Card"),
    CC_Bergners_Card("Bergners Card"),
    CC_JCPenney_Card("JCPenney Card"),
    CC_Chase_Card("Chase Card"),
    CC_Sears_Charge_Card("Sears Charge Card"),
    CC_Famous_Baar_Macys_Card("Famous Baar - Macys Card"),
    CC_Kohls_Card("Kohls Card"),
    CC_Providian_Card("Providian Card"),
    CC_Sodexo("Sodexo"),
    CC_SBI_Advantage_Dinesh("SBI Advantage - Dinesh"),
    CC_SBI_Advantage_Sebina("SBI Advantage - Sebina"),
    CC_HSBC_Dinesh("HSBC - Dinesh"),
    CC_HSBC_Sebina("HSBC - Sebina"),
    CC_SBI_Elite_Dinesh("SBI Elite - Dinesh"),
    CC_Amex_Dinesh("Amex - Dinesh"),
    CC_SBI_GM_Dinesh("SBI GM - Dinesh"),

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
    Other_Employer("Other Employer"),
    Digital_Misc("Misc"),
    Investment("Investment"),
    Investment_Interest("Investment Interest"),
    CreditCard_Payment("CreditCard Payment"),
    Cash("Cash"),
    Vendor_Cash("Vendor Cash");

    private final String value;

    SubAccount(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
