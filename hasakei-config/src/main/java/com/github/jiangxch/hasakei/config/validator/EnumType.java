package com.github.jiangxch.hasakei.config.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = EnumType.EnumValidator.class)
@Documented
public @interface EnumType {
    String message() default "EnumType is not illegal";

    Class<? extends Enum> value();

    class EnumValidator implements ConstraintValidator<EnumType, Integer> {
        private Field nameField = null;
        private Field typeField = null;
        private List<Integer> types = new ArrayList<>();

        @Override
        public void initialize(EnumType enumType) {
            Class<? extends Enum> value = enumType.value();
            Enum[] enums = value.getEnumConstants();
            Field[] declaredFields = value.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if ("name".equals(declaredField.getName())) {
                    nameField = declaredField;
                }
                if ("type".equals(declaredField.getName())) {
                    typeField = declaredField;
                }
            }
            if (typeField == null) {
                throw new RuntimeException("can't type field from enum  " + value.getName());
            }
            try {
                for (Enum enu : enums) {
                    Object type = typeField.get(enu);
                    if (type instanceof Integer) {
                        types.add((Integer) type);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("can't type field from obj,enum Class=" + value.getName() + ",ex=" + e.getMessage());
            }
        }

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            return types.contains(value);
        }
    }
}