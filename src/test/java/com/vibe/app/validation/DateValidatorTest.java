package com.vibe.app.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DateValidatorTest {

    private DateValidator validator;
    private ConstraintValidatorContext spyContext;
    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder mockBuilder;

    @Before
    public void setUp() {
        validator = new DateValidator();
        spyContext = spy(ConstraintValidatorContext.class);
    }

    @Test
    public void should_return_false_if_input_is_null() {
        when(spyContext.buildConstraintViolationWithTemplate("{com.vibe.app.validation.DateValidator.NullDate}")).thenReturn(mockBuilder);

        assertFalse(validator.isValid(null, spyContext));

        verify(spyContext).buildConstraintViolationWithTemplate("{com.vibe.app.validation.DateValidator.NullDate}");
        verify(mockBuilder).addConstraintViolation();

    }

    @Test
    public void should_return_false_if_input_is_not_in_MM_SLASH_DD_SLASH_YYYY_format() {
        when(spyContext.buildConstraintViolationWithTemplate("{com.vibe.app.validation.DateValidator.invalidDate}")).thenReturn(mockBuilder);

        assertFalse(validator.isValid("12251987", spyContext));

        verify(spyContext).buildConstraintViolationWithTemplate("{com.vibe.app.validation.DateValidator.invalidDate}");
        verify(mockBuilder).addConstraintViolation();

    }

    @Test
    public void should_return_true_if_input_is_in_MM_SLASH_DD_SLASH_YYYY_format() {
        assertTrue(validator.isValid("12/25/1987", spyContext));

        verifyZeroInteractions(mockBuilder);
    }

}