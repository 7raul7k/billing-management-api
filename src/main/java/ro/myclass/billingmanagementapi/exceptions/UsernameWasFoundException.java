package ro.myclass.billingmanagementapi.exceptions;

public class UsernameWasFoundException extends RuntimeException{

        public UsernameWasFoundException() {

            super("Username was found !");
        }
}
