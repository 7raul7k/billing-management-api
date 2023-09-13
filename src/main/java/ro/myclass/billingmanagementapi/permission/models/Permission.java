package ro.myclass.billingmanagementapi.permission.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.role.models.Role;
import ro.myclass.billingmanagementapi.validators.annotation.ModuleConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.TitleConstraint;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name = "permissions")
@Entity(name = "Permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Permission {
    @Id
    @SequenceGenerator(name = "permission_sequence",
    sequenceName = "permission_sequence",
    allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "permission_sequence"
    )
    @Column(name = "id")
    private long id;
    @Column(name = "title",
    nullable = false,
    columnDefinition = "TEXT")
    @TitleConstraint
    private String title;
    @Column(name = "module",
    nullable = false,
    columnDefinition = "TEXT")
    @ModuleConstraint
    private String module;
    @Column(name = "description",
    nullable = false,
    columnDefinition = "TEXT")
    private String description;


    @Override
    public String toString(){
        return id+","+title+","+module+","+description;
    }

    @Override
    public boolean equals(Object obj){
        Permission p = (Permission) obj;

        if(p.getTitle().equals(this.title)&&p.getModule().equals(this.module)&&p.getDescription().equals(this.description)){

            return true;
        }
        return false;
    }

    @ManyToOne
    @JoinColumn(name="role_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "role_id_fk"))
    @JsonBackReference(value = "test2")
    private Role role;

}
