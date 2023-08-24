package ro.myclass.billingmanagementapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UpdatePaymentRequest {

    private PaymentDTO paymentDTO;
    private int customerId;
}
