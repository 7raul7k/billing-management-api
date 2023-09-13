package ro.myclass.billingmanagementapi.bill.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.customer.models.Customer;
import ro.myclass.billingmanagementapi.receipt.models.Receipt;
import ro.myclass.billingmanagementapi.validators.annotation.NumberConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.TypeConstraint;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name ="bills")
@Entity(name = "Bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Bill {

    @Id
    @SequenceGenerator(name = "bill_sequence",
    sequenceName = "bill_sequence",
    allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "bill_sequence"
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "type",
    nullable = false,
    columnDefinition = "TEXT")
    @TypeConstraint
    private String type;
    @Column(name = "description",
    nullable = false,
    columnDefinition = "TEXT")
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @Column(name = "number",
    nullable = false,
    columnDefinition = "TEXT")
    @NumberConstraint
    private String number;

    @Override
    public String toString(){
        return id+","+type+","+description+","+number;
    }

    @Override
    public boolean equals(Object obj){
        Bill b = (Bill) obj;

        if(b.getType().equals(this.type)&&b.getDescription().equals(this.description)&&b.number.equals(this.number)){
            return true;
        }

        return false;
    }


    @ManyToOne
    @JoinColumn(name="customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "customer_id_fk"))
    @JsonBackReference(value = "test1")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="receipt_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "receipt_id_fk"))
    @JsonBackReference(value = "test10")
    private Receipt receipt;




}
