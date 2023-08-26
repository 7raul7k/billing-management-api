package ro.myclass.billingmanagementapi.paymentmode.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.paymentmode.models.PaymentMode;

import java.util.List;

@Service
public interface PaymentModeQuerryService {

    List<PaymentMode> getAllPaymentModes();

    PaymentMode getPaymentModeById(long id);

    PaymentMode getPaymentModeByName(String name);
}
