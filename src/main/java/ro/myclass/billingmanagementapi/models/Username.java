package ro.myclass.billingmanagementapi.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name = "usernames")
@Entity(name = "Username")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Username {

    @Id
    @SequenceGenerator(name = "username_sequence",
    sequenceName = "username_sequence",
    allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "username_sequence"
    )
    @Column(name = "id")
    private long id;
    @Column(name = "name",
    nullable = false,
    columnDefinition = "TEXT")
    private String username;
    @Column(name = "email",
    nullable = false,
    columnDefinition = "TEXT")
    private String email;
    @Column(name = "dob",
    nullable = false,
    columnDefinition = "DATE")
    private LocalDate dob;
    @Column(name = "address",
    nullable = false,
    columnDefinition = "TEXT")
    private String address;

    @Override
    public String toString(){
        return username+","+email+","+address;
    }

    @Override
    public boolean equals(Object obj){

        Username u = (Username) obj;

        if(u.username.equals(this.username)&&u.email.equals(this.email)&&u.address.equals(this.address)){
            return true;
        }

        return false;

    }

    @ManyToOne
    @JoinColumn(name="role_id",
    referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "role_id_fk"))
    @JsonBackReference(value = "test1")
    private Role role;

}
