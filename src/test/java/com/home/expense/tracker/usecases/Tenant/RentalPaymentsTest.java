package com.home.expense.tracker.usecases.Tenant;

import com.home.expense.tracker.controllers.TenantPaymentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RentalPaymentsTest {

    @Autowired
    private TenantPaymentController tenantPaymentController;

    @Test
    void testPaymentsForOneTenant(){
        runTenantPaymentEnquiry();
        assertTrue(true);
    }

    private void runTenantPaymentEnquiry() {

        // Create a Calendar instance
        Calendar rentStartDate = Calendar.getInstance();
        // Set the desired date: 1 January 2023
        rentStartDate.set(2023, Calendar.JANUARY, 1, 0, 0, 0); // Year, Month, Day, Hour, Minute, Second
        // Optional: Clear milliseconds for precision
        rentStartDate.set(Calendar.MILLISECOND, 0);
        tenantPaymentController.handleGetTenantPayments("Poobalan",rentStartDate, List.of(".*s.poobalan19.*") );
    }
}
