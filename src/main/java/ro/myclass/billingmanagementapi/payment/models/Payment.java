package ro.myclass.billingmanagementapi.payment.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.customer.models.Customer;
import ro.myclass.billingmanagementapi.validators.annotation.AmountConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.DateConstraint;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name = "payments")
@Entity(name ="Payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Payment {
    @Id
    @SequenceGenerator(name ="payment_sequence",
    sequenceName = "payment_sequence",
    allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "payment_sequence"
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "description",
    nullable = false,
    columnDefinition = "TEXT")
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @Column(name = "amount",
    nullable = false,
    columnDefinition = "TEXT")
    @AmountConstraint
    private String amount;
    @Column(name ="date",
    nullable = false,
    columnDefinition = "DATE")
    @DateConstraint
    private LocalDate date;

    @Override
    public String toString(){
        return id+","+description+","+amount+","+date;
    }

    @Override
    public boolean equals(Object obj){
        Payment p = (Payment) obj;

        if(p.getDescription().equals(this.description)&&p.getAmount().equals(this.amount)){
            return true;
        }

        return false;
    }

    @ManyToOne
    @JoinColumn(name="customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "customer_id_fk2"))
    @JsonBackReference(value = "test3")
    private Customer customer;
}
