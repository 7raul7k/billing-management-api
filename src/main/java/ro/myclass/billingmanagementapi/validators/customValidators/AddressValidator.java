package ro.myclass.billingmanagementapi.validators.customValidators;

import jakarta.validation.ConstraintValidator;
import ro.myclass.billingmanagementapi.validators.annotation.AddressConstraint;

public class AddressValidator implements ConstraintValidator<AddressConstraint,String> {

    @Override
    public void initialize(AddressConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String address, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        if(address != null){
            if(address.matches("^[a-zA-Z0-9\\s]*$")){
                return true;
            }
        }
        return false;
    }
}
