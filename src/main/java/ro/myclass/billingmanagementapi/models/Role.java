package ro.myclass.billingmanagementapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.permission.models.Permission;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String toString(){
        return id+","+title+","+description;
    }

    @Override
    public boolean equals(Object obj){
        Role role = (Role) obj;

       if(role.getTitle().equals(this.title)&&this.getDescription().equals(this.description)){
           return true;
       }

       return false;

    }


    @OneToMany(mappedBy = "role",
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.EAGER)
    @JsonBackReference(value = "test4")
    List<Username> usernameList = new ArrayList<>();

    @OneToMany(mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JsonBackReference(value = "test5")
    List<Permission> permissions = new ArrayList<>();

}
