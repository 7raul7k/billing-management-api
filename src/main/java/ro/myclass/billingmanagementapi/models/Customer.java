package ro.myclass.billingmanagementapi.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name = "customers")
@Entity(name = "Customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Customer {
    @Id
    @SequenceGenerator(name =  "customer_sequence",
    sequenceName = "customer_sequence",
    allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "customer_sequence"
    )
    @Column(name = "id")
    private long id;
    @Column(name =  "name",
    nullable = false,
    columnDefinition = "TEXT")
    private String name;
    @Column(name =  "mobile",
    nullable = false,
    columnDefinition = "TEXT")
    private String mobile;
    @Column(name = "email",
    nullable = false,
    columnDefinition = "TEXT")
    private String email;
    @Column(name = "address",
    nullable = false,
    columnDefinition = "TEXT")
    private String address;
    @Column(name = "username",
    nullable = false,
    columnDefinition = "TEXT")
    private String username;
    @Column(name = "password",
    nullable = false,
    columnDefinition = "TEXT")
    private String password;

    @Override
    public String toString(){
        return id+","+name +","+mobile+","+email+","+address+","+username+","+password;
    }
    @Override
    public boolean equals(Object obj){
        Customer c = (Customer) obj;

        if(c.getName().equals(this.name)&&c.getMobile().equals(this.mobile)&&c.getEmail().equals(this.email)&&c.getAddress().equals(this.address)&&c.getUsername().equals(this.username)&&c.getPassword().equals(this.password)){
            return true;
        }

        return false;
    }

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JsonBackReference(value = "test4")
    List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JsonBackReference(value = "test5")
    private List<Bill> bills = new ArrayList<>();
}
