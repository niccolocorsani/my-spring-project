package com.example.spring.demo.model.client;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckRegularExpressionValidator implements ConstraintValidator<CheckRegularExperssion, String> {


    @Override
    public void initialize(CheckRegularExperssion checkRegularExperssion) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null) {
            return value.matches("^[a-zA-Z]+");
        }
        return false;
    }
}




