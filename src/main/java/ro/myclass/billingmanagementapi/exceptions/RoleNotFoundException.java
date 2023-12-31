package ro.myclass.billingmanagementapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException() {

        super("Role not found exception");
    }
}
