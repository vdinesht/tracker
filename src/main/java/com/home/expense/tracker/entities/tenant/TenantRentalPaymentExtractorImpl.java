package com.home.expense.tracker.entities.tenant;

import com.home.expense.tracker.entities.PrimaryAccount;
import com.home.expense.tracker.entities.transaction.TransactionData;
import com.home.expense.tracker.entities.transaction.TransactionDataRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TenantRentalPaymentExtractorImpl implements TenantRentalPaymentExtractor {

    @Autowired
    private TransactionData transactionData;

    @Override
    public List<TransactionDataRow> getMatchingTransactions(LocalDate startDate, LocalDate endDate, List<String> tokens) {
        // Regular expression to match items starting with "a"
        String regex = tokens.get(0);
        Pattern pattern = Pattern.compile(regex);

        return transactionData.getDebitRows(startDate, endDate, PrimaryAccount.BankAsset).stream().filter(item -> pattern.matcher(item.description()).matches())
                .collect(Collectors.toList());
    }
}
