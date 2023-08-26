package ro.myclass.billingmanagementapi.paymentmode.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.paymentmode.dto.PaymentModeDTO;

@Service
@Transactional
public interface PaymentModeCommandService {

    void addPaymentMode(PaymentModeDTO paymentModeDTO);

    void deletePaymentMode(String name);

    void updatePaymentMode(PaymentModeDTO paymentModeDTO);

}
