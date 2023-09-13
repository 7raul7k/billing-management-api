package ro.myclass.billingmanagementapi.role.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.permission.models.Permission;
import ro.myclass.billingmanagementapi.role.models.Role;
import ro.myclass.billingmanagementapi.validators.annotation.TitleConstraint;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RoleDTO {

    @TitleConstraint
    private String title;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @NotNull(message = "Permissions cannot be null")
    private List<Permission> permissions;
    @NotNull(message = "Role cannot be null")
    private Role role;
}
