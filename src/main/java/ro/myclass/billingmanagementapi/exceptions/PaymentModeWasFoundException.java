package ro.myclass.billingmanagementapi.exceptions;

public class PaymentModeWasFoundException extends RuntimeException{
    public PaymentModeWasFoundException() {
        super("Payment mode was found");
    }
}
