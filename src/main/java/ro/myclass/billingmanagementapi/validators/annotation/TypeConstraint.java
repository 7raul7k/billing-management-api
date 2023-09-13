package ro.myclass.billingmanagementapi.validators.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ro.myclass.billingmanagementapi.validators.customValidators.TypeValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeConstraint  {
    String message() default "Type is not valid";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
