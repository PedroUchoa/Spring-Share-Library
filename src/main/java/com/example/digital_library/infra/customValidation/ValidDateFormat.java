package com.example.digital_library.infra.customValidation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDateFormatValidator.class)
@Documented
public @interface ValidDateFormat {
    String message() default "Invalid date format";
    String pattern();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
