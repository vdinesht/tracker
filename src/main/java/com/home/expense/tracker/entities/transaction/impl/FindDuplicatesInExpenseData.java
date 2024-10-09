package com.home.expense.tracker.entities.transaction.impl;


import com.home.expense.tracker.entities.transaction.TransactionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindDuplicatesInExpenseData {
    private static final Logger logger = LoggerFactory.getLogger(FindDuplicatesInExpenseData.class);

    public static List<Integer> findMatchingRowsExcludingDescription(TransactionData transactionData, LocalDate from){
        List<TransactionDataRowSubSet> duplicateRowList = transactionData.getRows(from, LocalDate.now())
                                                        .stream()
                                                        .map(row-> new TransactionDataRowSubSet(row))
                                                        .collect(Collectors.groupingBy(Function.identity()))
                                                        .entrySet()
                                                        .stream()
                                                        .filter(e -> e.getValue().size() > 1)
                                                        .map(e->e.getValue())
                                                        .flatMap(List::stream)
                                                        .toList();

        return duplicateRowList.stream().map(TransactionDataRowSubSet::id).toList();
    }
}
