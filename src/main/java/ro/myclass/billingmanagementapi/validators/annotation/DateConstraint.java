package ro.myclass.billingmanagementapi.validators.annotation;


import jakarta.validation.Constraint;
import ro.myclass.billingmanagementapi.validators.customValidators.DateValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateConstraint {

    String message() default "Date is not valid";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
