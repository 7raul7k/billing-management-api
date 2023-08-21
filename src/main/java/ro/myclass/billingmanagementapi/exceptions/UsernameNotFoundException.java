package ro.myclass.billingmanagementapi.exceptions;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException() {

        super("Username not found !");
    }
}
