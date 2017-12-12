package com.vibe.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.vibe.app.util.Helper.MM_SLASH_DD_SLASH_YYYY;

public class DateValidator implements ConstraintValidator<DateConstraint, String> {

    @Override
    public void initialize(DateConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String dateStr, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (null == dateStr) {
            context.buildConstraintViolationWithTemplate("{com.vibe.app.validation.DateValidator.NullDate}").addConstraintViolation();
            return false;
        }
        try {
            LocalDate localDate = LocalDate.parse(dateStr, MM_SLASH_DD_SLASH_YYYY);
        } catch (DateTimeParseException pe) {
            context.buildConstraintViolationWithTemplate("{com.vibe.app.validation.DateValidator.invalidDate}").addConstraintViolation();
            return false;
        }
        return true;
    }
}
