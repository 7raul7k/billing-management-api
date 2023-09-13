package ro.myclass.billingmanagementapi.validators.annotation;

import jakarta.validation.Constraint;
import ro.myclass.billingmanagementapi.validators.customValidators.AmountValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AmountValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AmountConstraint {

        String message() default "Amount is not valid";
        Class<?>[] groups() default {};
        Class<?>[] payload() default {};
}
