package ro.myclass.billingmanagementapi.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name ="payment_modes")
@Entity(name = "PaymentMode")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PaymentMode {

    @Id
    @SequenceGenerator(name = "payment_mode_sequence",
    sequenceName = "payment_mode_sequence",
    allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "payment_mode_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "name",
    nullable = false,
    columnDefinition = "TEXT")
    private String name;
    @Column(name = "type",
    nullable = false,
    columnDefinition = "TEXT")
    private String type;
    @Column(name ="description",
    nullable = false,
    columnDefinition = "TEXT")
    private String description;

    @Override
    public String toString(){
        return name+","+type+","+description;
    }

    @Override
    public boolean equals(Object obj){
        PaymentMode pm = (PaymentMode) obj;

        if(pm.getName().equals(this.name)&&pm.getType().equals(this.type)&&pm.getDescription().equals(this.description)){

            return true;
        }

        return false;
    }
}
