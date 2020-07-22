package com.github.jiangxch.hasakei.config.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = EnumType.EnumValidator.class)
@Documented
public @interface EnumType {
    String message() default "phone invalid";

    Class<? extends Annotation> value();

    class EnumValidator implements ConstraintValidator<EnumType,Integer> {

        @Override
        public void initialize(EnumType enumType) {
            Class<? extends Annotation> value = enumType.value();
            value.
        }

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            return false;
        }
    }
}