package ro.myclass.billingmanagementapi.validators.customValidators;

import jakarta.validation.ConstraintValidator;
import ro.myclass.billingmanagementapi.validators.annotation.UsernameConstraint;

public class UsernameValidator implements ConstraintValidator<UsernameConstraint,String> {

    @Override
    public void initialize(UsernameConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        if(username != null){
            if(username.matches("^[a-zA-Z0-9]*$")){
                return true;
            }
        }
        return false;
    }
}
