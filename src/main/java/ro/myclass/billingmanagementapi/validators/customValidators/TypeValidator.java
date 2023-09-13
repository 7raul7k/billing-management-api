package ro.myclass.billingmanagementapi.validators.customValidators;

import jakarta.validation.ConstraintValidator;
import ro.myclass.billingmanagementapi.validators.annotation.TypeConstraint;

public class TypeValidator  implements ConstraintValidator<TypeConstraint,String> {

    @Override
    public void initialize(TypeConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String type, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        if(type != null){
            if(type.equals("electricity")||type.equals("water")||type.equals("gas")||type.equals("internet")){
                return true;
            }
        }
        return false;
    }
}
