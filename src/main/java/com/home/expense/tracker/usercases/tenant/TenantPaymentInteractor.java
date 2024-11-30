package com.home.expense.tracker.usercases.tenant;

import com.home.expense.tracker.entities.tenant.TenantRentalPaymentExtractor;
import com.home.expense.tracker.presenter.TenantPaymentPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class TenantPaymentInteractor implements TenantPaymentInputBoundry{
    @Autowired
    private TenantPaymentPresenter tenantPaymentPresenter;

    @Autowired
    private TenantRentalPaymentExtractor tenantRentalPaymentExtractor;

    @Override
    public void getRentalPaymentDetails(TenantRentalEnquiry tenantRentalEnquiry) {
        TenantRentalResponse rentalResponse = new TenantRentalResponse(tenantRentalEnquiry.getName());

        LocalDate startDate = DateConversionUtils.calendarToLocalDate(tenantRentalEnquiry.getRentalStartDate());
        LocalDate endDate = DateConversionUtils.calendarToLocalDate(tenantRentalEnquiry.getRentalEndDate());

        rentalResponse.listPayments.addAll(tenantRentalPaymentExtractor.getMatchingTransactions(startDate, endDate, tenantRentalEnquiry.getMatchingTokens())
                                        .stream().map(TransactionDataRowToTenantPaymentsMapper::mapToTenantPayments).collect(Collectors.toList()));

        this.tenantPaymentPresenter.present(rentalResponse);
    }
}
