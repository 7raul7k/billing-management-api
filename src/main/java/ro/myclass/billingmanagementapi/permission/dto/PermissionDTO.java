package ro.myclass.billingmanagementapi.permission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.role.models.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PermissionDTO {

    private String title;
    private String description;
    private String module;
    private Role role;

}
