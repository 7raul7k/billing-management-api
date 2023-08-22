package ro.myclass.billingmanagementapi.exceptions;

public class CustomerWasFoundException extends RuntimeException{

    public CustomerWasFoundException() {
        super("Customer was found");
    }
}
