package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IbanValidator implements ConstraintValidator<ValidIban, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        String duzeltilmisIban = value.replaceAll("\\s+", "").toUpperCase();

        if (!duzeltilmisIban.matches("[A-Z]{2}[0-9]{24}")) {
            return false;
        }

        String duzenlenmisIban = duzeltilmisIban.substring(4) + duzeltilmisIban.substring(0,4);

        StringBuilder numerikStringBuilder = new StringBuilder();

        for (int i=0; i < duzenlenmisIban.length(); i++) {
            int codePoint = duzenlenmisIban.codePointAt(i);

            if (codePoint >= 65 && codePoint <= 90) {
                numerikStringBuilder.append(codePoint - 55);
            } else {
                numerikStringBuilder.append(duzenlenmisIban.charAt(i));
            }
        }

        String numerikString = numerikStringBuilder.toString();

        try {
            BigInteger ibanUzun = new BigInteger(numerikString);
            BigInteger mod97 = new BigInteger("97");

            return ibanUzun.mod(mod97).intValue() == 1;
        } catch (NumberFormatException e) {
            return false;
        }

    }

}
