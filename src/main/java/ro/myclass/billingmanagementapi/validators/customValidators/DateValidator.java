package ro.myclass.billingmanagementapi.validators.customValidators;

import jakarta.validation.ConstraintValidator;
import ro.myclass.billingmanagementapi.validators.annotation.DateConstraint;

import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<DateConstraint, LocalDate> {

    @Override
    public void initialize(DateConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate date, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        if(date != null){

                return true;

        }
        return false;
    }
}
