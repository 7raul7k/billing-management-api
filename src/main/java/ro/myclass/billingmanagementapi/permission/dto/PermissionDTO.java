package ro.myclass.billingmanagementapi.permission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.role.models.Role;
import ro.myclass.billingmanagementapi.validators.annotation.ModuleConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.TitleConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PermissionDTO {

    @TitleConstraint
    private String title;
    private String description;
    @ModuleConstraint
    private String module;
    private Role role;

}
