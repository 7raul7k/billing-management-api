package ro.myclass.billingmanagementapi.validators.customValidators;

import jakarta.validation.ConstraintValidator;
import ro.myclass.billingmanagementapi.validators.annotation.TitleConstraint;

public class TitleValidator implements ConstraintValidator<TitleConstraint, String> {

    @Override
    public void initialize(TitleConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String title, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        if(title != null){
            if(title.length()>=3 && title.length()<=50){
                return true;
            }
        }
        return false;
    }
}
