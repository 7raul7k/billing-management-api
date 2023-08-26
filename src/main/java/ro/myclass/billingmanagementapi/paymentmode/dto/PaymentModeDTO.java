package ro.myclass.billingmanagementapi.paymentmode.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PaymentModeDTO {

    private String name;
    private String type;
    private String description;

}
