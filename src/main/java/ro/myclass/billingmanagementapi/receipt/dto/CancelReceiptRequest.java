package ro.myclass.billingmanagementapi.receipt.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CancelReceiptRequest {


    @NotEmpty(message = "Type cannot be empty")
    private String type;
    @NotEmpty(message = "Number cannot be empty")
    private String number;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
}
