package ro.myclass.billingmanagementapi.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.bill.models.Bill;
import ro.myclass.billingmanagementapi.payment.models.Payment;
import ro.myclass.billingmanagementapi.validators.annotation.AddressConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.UsernameConstraint;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CustomerDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Mobile cannot be empty")
    private String mobile;
    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @AddressConstraint
    private String address;
    @UsernameConstraint
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @NotNull(message = "Payments cannot be null")
    private List<Payment> payments;
    @NotNull(message = "Bills cannot be null")
    private List<Bill> bills;


}
