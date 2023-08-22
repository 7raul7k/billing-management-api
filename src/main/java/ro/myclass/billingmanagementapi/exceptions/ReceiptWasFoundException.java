package ro.myclass.billingmanagementapi.exceptions;

public class ReceiptWasFoundException extends RuntimeException{

    public ReceiptWasFoundException() {
        super("Receipt was found");
    }
}
