package ro.myclass.billingmanagementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.bill.models.Bill;
import ro.myclass.billingmanagementapi.models.Payment;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CustomerDTO {

    private String name;
    private String mobile;
    private String email;
    private String address;
    private String username;
    private String password;
    private List<Payment> payments;
    private List<Bill> bills;


}
