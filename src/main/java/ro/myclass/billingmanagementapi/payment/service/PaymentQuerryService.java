package ro.myclass.billingmanagementapi.payment.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.payment.models.Payment;

import java.util.List;

@Service
public interface PaymentQuerryService {

    List<Payment> getAllPayments();

    Payment getPaymentByAmountAndDescription(String amount, String description);

    Payment getPaymentById(long id);

    List<Payment> getPaymentByAmount(String amount);



}
