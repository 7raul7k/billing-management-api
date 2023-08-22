package ro.myclass.billingmanagementapi.exceptions;

public class ReceiptNotFoundException extends RuntimeException{
    public ReceiptNotFoundException() {
        super("Receipt not found");
    }
}
