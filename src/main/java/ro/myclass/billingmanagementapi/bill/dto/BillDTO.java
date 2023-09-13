package ro.myclass.billingmanagementapi.bill.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.customer.models.Customer;
import ro.myclass.billingmanagementapi.receipt.models.Receipt;
import ro.myclass.billingmanagementapi.validators.annotation.NumberConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.TypeConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BillDTO {

    @TypeConstraint
    private String type;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @NumberConstraint
    private String number;
    @NotNull(message = "Customer cannot be null")
    private Customer customer;
    @NotNull(message = "Receipt cannot be null")
    private Receipt receipt;


}
