package ro.myclass.billingmanagementapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PaymentModeWasFoundException extends RuntimeException{
    public PaymentModeWasFoundException() {
        super("Payment mode was found");
    }
}
