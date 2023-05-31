package com.example.demo.validator.annotation;
import com.example.demo.validator.ValidUsernameValidator;
import jakarta.validation.Payload;
import jakarta.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidUsernameValidator.class)

public @interface ValidUserId {
    String message() default "Username already exits";
    Class<?>[] groups() default  {};
    Class<? extends Payload>[] payload() default {};
}
