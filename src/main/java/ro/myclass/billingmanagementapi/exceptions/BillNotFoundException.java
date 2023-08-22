package ro.myclass.billingmanagementapi.exceptions;

public class BillNotFoundException extends RuntimeException{

        public BillNotFoundException() {
            super("Bill not found");
        }
}
