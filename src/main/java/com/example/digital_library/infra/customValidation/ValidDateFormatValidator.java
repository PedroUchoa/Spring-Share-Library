package com.example.digital_library.infra.customValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidDateFormatValidator implements ConstraintValidator<ValidDateFormat,LocalDate> {

    private String pattern;

    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(LocalDate s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null ){
            return true;
        }
        try{
            String formatted = s.format(DateTimeFormatter.ofPattern(pattern));
            LocalDate parsed = LocalDate.parse(formatted,DateTimeFormatter.ofPattern(pattern));
            return parsed.equals(s);
        }catch (DateTimeParseException e){
            return false;
        }
    }
}
