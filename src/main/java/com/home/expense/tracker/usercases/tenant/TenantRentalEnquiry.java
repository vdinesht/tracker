package com.home.expense.tracker.usercases.tenant;

import java.util.Calendar;
import java.util.List;

public class TenantRentalEnquiry {
    private final String name;
    private final Calendar rentalStartDate;
    private final Calendar rentalEndDate;
    private final List<String> matchingTokens;


    public TenantRentalEnquiry(String name, Calendar rentalStartDate, Calendar rentalEndDate, List<String> matchingTokens) {
        this.name = name;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.matchingTokens = matchingTokens;
    }

    public String getName() {
        return name;
    }

    public Calendar getRentalStartDate() {
        return rentalStartDate;
    }

    public Calendar getRentalEndDate() {
        return rentalEndDate;
    }

    public List<String> getMatchingTokens() {
        return matchingTokens;
    }
}
