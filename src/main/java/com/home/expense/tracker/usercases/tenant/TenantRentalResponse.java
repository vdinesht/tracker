package com.home.expense.tracker.usercases.tenant;

import java.util.ArrayList;
import java.util.List;

public class TenantRentalResponse {
    String name;
    List<TenantPayments> listPayments;

    public TenantRentalResponse(String name) {
        this.name = name;
        this.listPayments = new ArrayList<>();
    }

    public void addPayments(TenantPayments rentalPayment){
        this.listPayments.add(rentalPayment);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TenantRentalResponse {\n");
        sb.append("  Name: ").append(name).append(",\n");
        sb.append("  Payments:\n");

        if (listPayments.isEmpty()) {
            sb.append("    No payments recorded.\n");
        } else {
            for (TenantPayments payment : listPayments) {
                sb.append("    - ").append(formatTenantPayment(payment)).append("\n");
            }
        }

        sb.append("}");
        return sb.toString();
    }

    private String formatTenantPayment(TenantPayments payment) {
        return String.format(
                "Date: %tF, Description: %s, Bank Account: %s, Amount: %.2f",
                payment.transactionDate, // Formats the Calendar as "YYYY-MM-DD"
                payment.description,
                payment.bankAccount,
                payment.amount
        );
    }

}
