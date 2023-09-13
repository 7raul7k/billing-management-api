package ro.myclass.billingmanagementapi.validators.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ro.myclass.billingmanagementapi.validators.customValidators.AddressValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AddressValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AddressConstraint {
    String message() default "Address is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
