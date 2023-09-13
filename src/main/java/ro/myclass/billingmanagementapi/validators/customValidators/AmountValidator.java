package ro.myclass.billingmanagementapi.validators.customValidators;

import jakarta.validation.ConstraintValidator;
import ro.myclass.billingmanagementapi.validators.annotation.AmountConstraint;

public class AmountValidator implements ConstraintValidator<AmountConstraint,String> {

        @Override
        public void initialize(AmountConstraint constraintAnnotation) {
        }

        @Override
        public boolean isValid(String amount, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
            if(amount != null){
                if(amount.matches("[0-9]+")){
                    return true;
                }
            }
            return false;
        }


}
