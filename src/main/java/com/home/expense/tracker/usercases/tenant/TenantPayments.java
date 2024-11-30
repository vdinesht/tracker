package com.home.expense.tracker.usercases.tenant;

import com.home.expense.tracker.entities.SubAccount;

import java.util.Calendar;

public class TenantPayments {
    Calendar transactionDate;
    String description;
    SubAccount bankAccount;
    Double amount;

    public TenantPayments(Calendar transactionDate, String description, SubAccount bankAccount, Double amount) {
        this.transactionDate = transactionDate;
        this.description = description;
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TenantPayments{" +
                "transactionDate=" + transactionDate +
                ", description='" + description + '\'' +
                ", bankAccount=" + bankAccount +
                ", amount=" + amount +
                '}';
    }
}
