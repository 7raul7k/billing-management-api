package ro.myclass.billingmanagementapi.validators.customValidators;

import jakarta.validation.ConstraintValidator;
import ro.myclass.billingmanagementapi.validators.annotation.NumberConstraint;

public class NumberValidator implements ConstraintValidator<NumberConstraint, String> {

    @Override
    public void initialize(NumberConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String number, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        if(number != null){
            if(number.length()>=1){
                return true;
            }
        }
        return false;
    }
}

