package ro.myclass.billingmanagementapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name = "roles")
@Entity(name ="Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Role {
    @Id
    @SequenceGenerator(name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "role_sequence"
    )
    @Column(name = "id")
    private long id;
    @Column(name = "title",
            nullable = false,
            columnDefinition = "TEXT")
    private String title;
    @Column(name = "description",
            nullable = false,
            columnDefinition = "TEXT")
    private String description;

}
