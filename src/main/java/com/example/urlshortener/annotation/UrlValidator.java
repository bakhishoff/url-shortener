package com.example.urlshortener.annotation;

import com.example.urlshortener.component.UrlChecker;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Ulphat
 */
@RequiredArgsConstructor
public class UrlValidator extends org.apache.commons.validator.routines.UrlValidator implements ConstraintValidator<Url, String> {

    private final UrlChecker urlChecker;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && isValid(value) && urlChecker.check(value);
    }
}
