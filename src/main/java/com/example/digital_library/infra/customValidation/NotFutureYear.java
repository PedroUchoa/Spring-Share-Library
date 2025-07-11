package com.example.digital_library.infra.customValidation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotFutureYearValidator.class)
@Documented
public @interface NotFutureYear {
    String message() default "year cannot be in the future";
    Class<?>[] groups() default {};
    Class <? extends Payload>[] payload() default{};


}
