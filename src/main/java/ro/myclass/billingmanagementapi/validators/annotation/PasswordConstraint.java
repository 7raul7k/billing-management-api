package ro.myclass.billingmanagementapi.validators.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ro.myclass.billingmanagementapi.validators.customValidators.PasswordValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Password is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
