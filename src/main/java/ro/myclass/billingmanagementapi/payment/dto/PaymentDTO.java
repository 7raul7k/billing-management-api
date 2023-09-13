package ro.myclass.billingmanagementapi.payment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.customer.models.Customer;
import ro.myclass.billingmanagementapi.validators.annotation.AmountConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.DateConstraint;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PaymentDTO {

    private String description;
    @AmountConstraint
    private String amount;
    @DateConstraint
    private LocalDate date;
    private Customer customer;


}
