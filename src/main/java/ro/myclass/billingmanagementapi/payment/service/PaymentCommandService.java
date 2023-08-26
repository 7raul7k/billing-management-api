package ro.myclass.billingmanagementapi.payment.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.payment.dto.PaymentDTO;
import ro.myclass.billingmanagementapi.payment.dto.UpdatePaymentRequest;

@Service
@Transactional
public interface PaymentCommandService {

    void addPayment(PaymentDTO paymentDTO);

    void deletePayment(String amount,String description);

    void updatePayment(UpdatePaymentRequest updatePaymentRequest);
}
