package ro.myclass.billingmanagementapi.exceptions;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException() {

        super("Role not found exception");
    }
}
