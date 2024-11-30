package com.home.expense.tracker.usercases.tenant;

import com.home.expense.tracker.entities.SubAccount;
import com.home.expense.tracker.entities.transaction.TransactionDataRow;

import java.util.Calendar;

public class TransactionDataRowToTenantPaymentsMapper {
    public static TenantPayments mapToTenantPayments(TransactionDataRow transactionDataRow) {
        // Convert LocalDate to Calendar
        Calendar transactionDate = DateConversionUtils.localDateToCalendar(transactionDataRow.date());

        // Extract relevant fields
        String description = transactionDataRow.description();
        SubAccount bankAccount = transactionDataRow.debitSubAccount(); // Assuming bank account corresponds to debitSubAccount
        Double amount = transactionDataRow.amount();

        // Create and return TenantPayments object
        return new TenantPayments(transactionDate, description, bankAccount, amount);
    }

}
