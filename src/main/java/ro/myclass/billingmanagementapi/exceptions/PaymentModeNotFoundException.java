package ro.myclass.billingmanagementapi.exceptions;

public class PaymentModeNotFoundException extends RuntimeException{
    public PaymentModeNotFoundException() {
        super("Payment mode not found");
    }
}
