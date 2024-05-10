package com.home.expense.tracker.core;

public enum CreditCard {
    Gift_Card("Gift Card"),
    Shell_Charge_Card("Shell Charge Card"),
    CEFCU_Dinesh("CEFCU - Dinesh"),
    Sears_Master_Card("Sears Master Card"),
    Sams_Club_Discover_Card("Sams Club Discover Card"),
    Bergners_Card("Bergners Card"),
    JCPenney_Card("JCPenney Card"),
    Chase_Card("Chase Card"),
    Sears_Charge_Card("Sears Charge Card"),
    Famous_Baar_Macys_Card("Famous Baar - Macys Card"),
    Kohls_Card("Kohls Card"),
    Providian_Card("Providian Card"),
    Sodexo("Sodexo"),
    SBI_Advantage_Dinesh("SBI Advantage - Dinesh"),
    SBI_Advantage_Sebina("SBI Advantage - Sebina"),
    HSBC_Dinesh("HSBC - Dinesh"),
    HSBC_Sebina("HSBC - Sebina"),
    SBI_Elite_Dinesh("SBI Elite - Dinesh"),
    Amex_Dinesh("Amex - Dinesh"),
    SBI_GM_Dinesh("SBI GM - Dinesh");


    private final String value;

    CreditCard(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
