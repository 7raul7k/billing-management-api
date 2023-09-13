package ro.myclass.billingmanagementapi.username.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.role.models.Role;
import ro.myclass.billingmanagementapi.validators.annotation.AddressConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.EmailConstraint;
import ro.myclass.billingmanagementapi.validators.annotation.UsernameConstraint;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UsernameDTO {

        @UsernameConstraint
        private String username;
        @EmailConstraint
        private String email;
        @NotEmpty(message = "Data cannot be empty")
        private LocalDate dob;
        @AddressConstraint
        private String address;
        @NotEmpty(message = "Password cannot be empty")
        private Role Role;


}
