package ro.myclass.billingmanagementapi.validators.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ro.myclass.billingmanagementapi.validators.customValidators.NumberValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberConstraint {
    String message() default "Number is not valid";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
