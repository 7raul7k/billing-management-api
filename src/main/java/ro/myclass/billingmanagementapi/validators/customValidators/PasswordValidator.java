package ro.myclass.billingmanagementapi.validators.customValidators;

import jakarta.validation.ConstraintValidator;
import ro.myclass.billingmanagementapi.validators.annotation.PasswordConstraint;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint,String> {

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        if(password != null){
            if(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
                return true;
            }
        }
        return false;
    }
}
