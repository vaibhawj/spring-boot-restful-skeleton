package com.vibe.app.validation;

import com.vibe.app.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateValidator implements ConstraintValidator<DateConstraint, String> {

    @Autowired
    private Environment environment;

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
            LocalDate localDate = LocalDate.parse(dateStr, Helper.getFormatter(environment.getProperty("date.format")));
        } catch (DateTimeParseException pe) {
            context.buildConstraintViolationWithTemplate("{com.vibe.app.validation.DateValidator.invalidDate}").addConstraintViolation();
            return false;
        }
        return true;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
