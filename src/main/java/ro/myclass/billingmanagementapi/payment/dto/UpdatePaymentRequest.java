package ro.myclass.billingmanagementapi.payment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.payment.dto.PaymentDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UpdatePaymentRequest {

    private PaymentDTO paymentDTO;
    private int customerId;
}
