package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TCNoValidator implements ConstraintValidator<ValidTCNo, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        if (!value.matches("[1-9]{1}[0-9]{10}")) {
            return false;
        }

        int [] num = new int[11];
        for (int i = 0; i < 11; i++) {
            num[i] = value.charAt(i);
        }

        int sumOfOddPositionedDigits = num[0] + num[2] + num[4] + num[6] + num[8];
        int sumOfEvenPositionedDigits = num[1] + num[3] + num[5] + num[7];

        int moduloTen = ((sumOfOddPositionedDigits * 7) - sumOfEvenPositionedDigits) % 10;

        if (moduloTen < 0) {
            moduloTen += 10;
        }

        if (num[9] != moduloTen) {
        return false;
        }

        int sumOfFirstThenDigits = 0;
        for (int i = 0; i < 10; i++) {
            sumOfFirstThenDigits += num[i];
        }

        if (num[10] != sumOfFirstThenDigits % 10) {
            return false;
        }

        return true;
    }

}
