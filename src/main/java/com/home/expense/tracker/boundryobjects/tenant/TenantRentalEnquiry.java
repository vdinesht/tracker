package com.home.expense.tracker.boundryobjects.tenant;

import com.home.expense.tracker.entities.SubAccount;

import java.util.Calendar;
import java.util.List;

public class TenantRentalEnquiry {
    String tenantName;
    Calendar rentalStartDate;
    Calendar rentalEndDate;
    SubAccount rentalReceiptBankAccount;
    List<String> matchingTokens;
}
