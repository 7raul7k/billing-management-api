package ro.myclass.billingmanagementapi.exceptions;

public class PermissionNotFoundException extends RuntimeException{

    public PermissionNotFoundException() {

        super("Permission not found !");
    }
}
