package ro.myclass.billingmanagementapi.validators.customValidators;

import jakarta.validation.ConstraintValidator;
import ro.myclass.billingmanagementapi.validators.annotation.TypeConstraint;

public class TypeValidator  implements ConstraintValidator<TypeConstraint,String> {

    @Override
    public void initialize(TypeConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String type, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        return type.equals("income") || type.equals("expense");
    }
}
