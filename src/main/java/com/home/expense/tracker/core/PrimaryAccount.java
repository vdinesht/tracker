package com.home.expense.tracker.core;

public enum PrimaryAccount {
    CashAsset("CashAsset"),
    BankAsset("BankAsset"),
    CreditCard("CreditCard"),
    CashPay("CashPay"),
    DigitalPay("DigitalPay");

    private final String value;

    PrimaryAccount(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
