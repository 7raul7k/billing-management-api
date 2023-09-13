package ro.myclass.billingmanagementapi.validators.customValidators;

import jakarta.validation.ConstraintValidator;
import ro.myclass.billingmanagementapi.validators.annotation.EmailConstraint;

public class EmailValidator implements ConstraintValidator<EmailConstraint,String> {

    @Override
    public void initialize(EmailConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        if(email != null){
            if(email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
                return true;
            }
        }
        return false;
    }
}
