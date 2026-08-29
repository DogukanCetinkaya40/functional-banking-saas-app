package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TCNoValidator.class)
@Documented
public @interface ValidTCNo {

    String message() default "Geçersiz TC Kimlik Numarası!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
