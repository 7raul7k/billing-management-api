package ro.myclass.billingmanagementapi.validators.customValidators;

import jakarta.validation.ConstraintValidator;
import ro.myclass.billingmanagementapi.validators.annotation.ModuleConstraint;

public class ModuleValidator implements ConstraintValidator<ModuleConstraint,String> {

    @Override
    public void initialize(ModuleConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String module, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        if(module != null){
            if(module.equals("user")||module.equals("role")||module.equals("permission")||module.equals("paymentMode")){
                return true;
            }
        }
        return false;
    }
}

