package ro.myclass.billingmanagementapi.bill.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.customer.models.Customer;
import ro.myclass.billingmanagementapi.receipt.models.Receipt;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BillDTO {

    private String type;
    private String description;
    private String number;
    private Customer customer;
    private Receipt receipt;


}
