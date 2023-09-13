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
import ro.myclass.billingmanagementapi.validators.annotation.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CustomerDTO {

    @UsernameConstraint
    private String name;
    @NumberConstraint
    private String mobile;
    @Email
    @EmailConstraint
    private String email;
    @AddressConstraint
    private String address;
    @UsernameConstraint
    private String username;
    @PasswordConstraint
    private String password;
    @NotNull(message = "Payments cannot be null")
    private List<Payment> payments;
    @NotNull(message = "Bills cannot be null")
    private List<Bill> bills;


}
