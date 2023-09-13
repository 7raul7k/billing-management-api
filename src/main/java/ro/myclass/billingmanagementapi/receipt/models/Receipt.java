package ro.myclass.billingmanagementapi.receipt.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.bill.models.Bill;
import ro.myclass.billingmanagementapi.validators.annotation.DateConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.TypeConstraint;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name = "receipts")
@Entity(name = "Receipt")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Receipt {

    @Id
    @SequenceGenerator(name = "receipt_sequence",
    sequenceName = "receipt_sequence",
    allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "receipt_sequence"
    )
    @Column( name = "id")
    private Long id;
    @Column(name = "type",
    nullable = false,
    columnDefinition = "TEXT")
    @TypeConstraint
    private String type;
    @Column(name = "description",
    nullable = false,
    columnDefinition = "TEXT")
    private String description;
    @Column(name = "number",
    nullable = false,
    columnDefinition = "TEXT")
    private String number;
    @Column(name = "date",
    nullable = false,
    columnDefinition = "DATE")
    @DateConstraint
    private LocalDate date;

    @Override
    public String toString(){
        return id+","+type+","+description+","+number+","+date;
    }

    @Override
    public boolean equals(Object obj){
        Receipt r = (Receipt) obj;

        if(r.getType().equals(this.type)&&r.getDescription().equals(this.description)&&r.getNumber().equals(this.number)){
            return true;
        }

        return false;


    }

    @OneToMany(mappedBy = "receipt",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JsonBackReference(value = "test5")
    private List<Bill> billList = new ArrayList<>();
}
