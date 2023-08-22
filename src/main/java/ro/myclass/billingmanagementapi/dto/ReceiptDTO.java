package ro.myclass.billingmanagementapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.models.Bill;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReceiptDTO {

    public String description;
    public String type;
    public String number;
    public LocalDate date;
    public List<Bill> bills;}
