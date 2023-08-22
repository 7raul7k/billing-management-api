package ro.myclass.billingmanagementapi.exceptions;

public class BillWasFoundException extends RuntimeException{

        public BillWasFoundException() {
            super("Bill was found");
        }
}
