package com.example.digital_library.infra.customValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class NotFutureYearValidator implements ConstraintValidator<NotFutureYear, Integer> {
    @Override
    public void initialize(NotFutureYear constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        if(year == null){
            return true;
        }
        return year <= LocalDate.now().getYear();
    }
}
