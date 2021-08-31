package com.example.urlshortener.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Ulphat
 */
@Documented
@Constraint(validatedBy = UrlValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Url {

    String message() default "{com.example.urlshorter.annotation.Url.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
