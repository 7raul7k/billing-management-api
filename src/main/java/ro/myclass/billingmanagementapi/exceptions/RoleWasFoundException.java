package ro.myclass.billingmanagementapi.exceptions;

public class RoleWasFoundException extends RuntimeException{

    public RoleWasFoundException() {
        super("Role was found!");
    }
}
