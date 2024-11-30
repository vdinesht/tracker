package com.home.expense.tracker.controllers;

import com.home.expense.tracker.usercases.tenant.TenantPaymentInteractor;
import com.home.expense.tracker.usercases.tenant.TenantRentalEnquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class TenantPaymentController {
    @Autowired
    private TenantPaymentInteractor tenantPaymentInteractor;

    public void handleGetTenantPayments(String tenantName, Calendar rentalStartDate, List<String> matchingTokens){
        TenantRentalEnquiry tenantRentalEnquiry = new TenantRentalEnquiry(tenantName, rentalStartDate, Calendar.getInstance(),matchingTokens);
        tenantPaymentInteractor.getRentalPaymentDetails(tenantRentalEnquiry);

    }
}
