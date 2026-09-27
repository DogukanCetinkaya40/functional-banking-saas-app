package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IbanValidator.class)
@Documented
public @interface ValidIban {

    String message() default "Geçersiz IBAN!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
