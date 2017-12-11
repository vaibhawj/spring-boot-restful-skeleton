package com.vibe.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import static com.vibe.app.util.Helper.formatter;

public class DateValidator implements ConstraintValidator<DateConstraint, String> {

    @Override
    public void initialize(DateConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (null == value) {
            return false;
        }
        try {
            formatter.withLocale(Locale.ENGLISH);
            LocalDate localDate = LocalDate.parse(value, formatter);
        } catch (DateTimeParseException pe) {
            return false;
        }
        return true;
    }
}
