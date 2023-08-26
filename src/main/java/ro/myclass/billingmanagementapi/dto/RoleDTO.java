package ro.myclass.billingmanagementapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.permission.models.Permission;
import ro.myclass.billingmanagementapi.models.Role;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RoleDTO {

    private String title;
    private String description;
    private List<Permission> permissions;
    private Role role;
}
