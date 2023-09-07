package ro.myclass.billingmanagementapi.username.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.billingmanagementapi.role.models.Role;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UsernameDTO {

        @NotEmpty(message = "Username cannot be empty")
        private String username;
        @Email(message = "Email should be valid")
        private String email;
        @NotEmpty(message = "Data cannot be empty")
        private LocalDate dob;
        @NotEmpty(message = "Address cannot be empty")
        private String address;
        @NotEmpty(message = "Password cannot be empty")
        private Role Role;


}
