package ro.myclass.billingmanagementapi.exceptions;

public class ListEmptyException extends RuntimeException{

    public ListEmptyException(){
        super("empty List!");
    }
}
