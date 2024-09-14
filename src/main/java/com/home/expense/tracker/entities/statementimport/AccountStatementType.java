package com.home.expense.tracker.entities.statementimport;

public enum AccountStatementType {
    Unknown(""),
    BankICICINagercoilJoint("609501090054"),
    BankICICIThoraipakkamDinesh("021201584122"),
    BankICICIThoraipakkamSebina("021201584123"),
    CreditCardSBIEliteDinesh("5362987434751094");

    private final String accountNumber;

    AccountStatementType(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

}
