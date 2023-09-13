package ro.myclass.billingmanagementapi.paymentmode.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.validators.annotation.TypeConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.UsernameConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PaymentModeDTO {
    @UsernameConstraint

    private String name;
    @TypeConstraint
    private String type;
    private String description;

}
