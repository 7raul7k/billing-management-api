package ro.myclass.billingmanagementapi.receipt.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.bill.models.Bill;
import ro.myclass.billingmanagementapi.validators.annotation.DateConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.TypeConstraint;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReceiptDTO {

    @NotEmpty(message = "Type cannot be empty")
    public String description;
    @TypeConstraint
    public String type;
    @NotEmpty(message = "Description cannot be empty")
    public String number;
    @DateConstraint
    public LocalDate date;
    @NotNull(message = "Bills cannot be null")
    public List<Bill> bills;}
