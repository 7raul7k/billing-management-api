package ro.myclass.billingmanagementapi.username.dto;

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

        private String username;
        private String email;
        private LocalDate dob;
        private String address;
        private Role Role;


}