package com.example.spring.demo.model.client;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target( {ElementType.TYPE, ElementType.LOCAL_VARIABLE,ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckRegularExpressionValidator.class)
public @interface CheckRegularExperssion {

    String message() default "not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
