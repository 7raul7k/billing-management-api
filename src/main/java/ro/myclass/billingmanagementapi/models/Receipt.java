package ro.myclass.billingmanagementapi.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

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
    private LocalDate date;

    @Override
    public String toString(){
        return type+","+description+","+number+","+date;
    }

    @Override
    public boolean equals(Object obj){
        Receipt r = (Receipt) obj;

        if(r.getType().equals(this.type)&&r.getDescription().equals(this.description)&&r.getNumber().equals(this.number)){
            return true;
        }

        return false;
    }
}
