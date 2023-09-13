package ro.myclass.billingmanagementapi.username.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.role.models.Role;
import ro.myclass.billingmanagementapi.validators.annotation.AddressConstraint;

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
    @NotEmpty(message = "Name cannot be empty")
    private String username;
    @Column(name = "email",
    nullable = false,
    columnDefinition = "TEXT")
    @Email(message = "Email should be valid")
    private String email;
    @Column(name = "dob",
    nullable = false,
    columnDefinition = "DATE")
    @NotEmpty(message = "Date of birth cannot be empty")
    private LocalDate dob;
    @Column(name = "address",
    nullable = false,
    columnDefinition = "TEXT")
    @AddressConstraint
    private String address;

    @Override
    public String toString(){
        return id+","+username+","+email+","+address;
    }

    @Override
    public boolean equals(Object obj){

        Username u = (Username) obj;

        if(u.getUsername().equals(this.username)&&u.getEmail().equals(this.email)&&u.getAddress().equals(this.address)){
            return true;
        }

        return false;

    }

    @ManyToOne
    @JoinColumn(name="role_id",
    referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "role_id_fk2"))
    @JsonBackReference(value = "test1")
    private Role role;

}
