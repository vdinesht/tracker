package com.home.expense.tracker.entities.transaction.impl;

import com.home.expense.tracker.entities.transaction.TransactionDataRow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateTransactionDetector {

    public static List<TransactionDataRow> identifyDuplicatesWithDescription(List<TransactionDataRow> transactions) {
        // Set to track unique transaction keys
        Set<String> uniqueKeys = new HashSet<>();
        List<TransactionDataRow> duplicates = new ArrayList<>();

        // Define date formatter for normalization
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (TransactionDataRow transaction : transactions) {
            // Step 1: Normalize fields
            String normalizedDate = transaction.date().toString();
            String normalizedDescription = normalizeDescription(transaction.description());
            String normalizedDetails = normalizeDescription(transaction.details());
            double roundedAmount = Math.round(transaction.amount() * 100.0) / 100.0;

            // Step 2: Generate unique key
            String uniqueKey = normalizedDate + "|" + roundedAmount + "|" + normalizedDescription + "|" + normalizedDetails;

            // Step 3: Check for duplicates
            if (uniqueKeys.contains(uniqueKey)) {
                duplicates.add(transaction);
            } else {
                uniqueKeys.add(uniqueKey);
            }
        }

        return duplicates;
    }

    public static List<TransactionDataRow> identifyDuplicatesWithAccounts(List<TransactionDataRow> transactions) {
        // Set to track unique transaction keys
        Set<String> uniqueKeys = new HashSet<>();
        List<TransactionDataRow> duplicates = new ArrayList<>();

        // Define date formatter for normalization
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (TransactionDataRow transaction : transactions) {
            // Step 1: Normalize fields
            String normalizedDate = transaction.date().toString();
            double roundedAmount = Math.round(transaction.amount() * 100.0) / 100.0;
            String debitAccount = transaction.debitAccount() != null ? transaction.debitAccount().toString() : "null";
            String creditAccount = transaction.creditAccount() != null ? transaction.creditAccount().toString() : "null";

            // Step 2: Generate unique key
            String uniqueKey = normalizedDate + "|" + roundedAmount + "|" + debitAccount + "|" + creditAccount;

            // Step 3: Check for duplicates
            if (uniqueKeys.contains(uniqueKey)) {
                duplicates.add(transaction);
            } else {
                uniqueKeys.add(uniqueKey);
            }
        }

        return duplicates;
    }
    // Normalize the description (convert to lowercase, remove extra whitespace)
    private static String normalizeDescription(String description) {
        if (description == null || description.isBlank()) {
            return "null";
        }
        return description.trim().toLowerCase().replaceAll("\\s+", " ");
    }
}
