package com.home.expense.tracker.presenter;

import com.home.expense.tracker.usercases.tenant.TenantPaymentOutputBoundry;
import com.home.expense.tracker.usercases.tenant.TenantRentalResponse;
import org.springframework.stereotype.Service;

@Service
public class TenantPaymentPresenter implements TenantPaymentOutputBoundry {

    @Override
    public void present(TenantRentalResponse rentalPaymentResponse) {
        System.out.println(rentalPaymentResponse);
    }
}
