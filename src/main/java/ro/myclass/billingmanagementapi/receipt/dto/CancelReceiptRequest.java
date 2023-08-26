package ro.myclass.billingmanagementapi.receipt.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CancelReceiptRequest {

    private String type;
    private String number;
    private String description;
}
