package ro.myclass.billingmanagementapi.validators.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ro.myclass.billingmanagementapi.validators.customValidators.ModuleValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ModuleValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleConstraint {

    String message() default "Module is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
