package vn.hoidanit.laptopshop.service.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

// Tao annotation ==> custom 1 annotation
@Constraint(validatedBy = RegisterDTOValidator.class)
@Target({ ElementType.TYPE }) // ==> annotation cho Class
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterDTOCheck {
    String message() default "Must be longer 8 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}