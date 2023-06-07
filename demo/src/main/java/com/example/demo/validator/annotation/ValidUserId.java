package com.example.demo.validator.annotation;
import com.example.demo.validator.ValidUserIdValidator;
import com.example.demo.validator.ValidUsernameValidator;
import jakarta.validation.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidUserIdValidator.class)
@Documented
public @interface ValidUserId {
    String message() default "Invailid User ID";
    Class<?>[] groups() default  {};
    Class<? extends Payload>[] payload() default {};
}
