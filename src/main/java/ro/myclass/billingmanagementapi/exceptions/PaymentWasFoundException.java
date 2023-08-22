package ro.myclass.billingmanagementapi.exceptions;

public class PaymentWasFoundException extends RuntimeException{

        public PaymentWasFoundException() {
            super("Payment was found");
        }
}
