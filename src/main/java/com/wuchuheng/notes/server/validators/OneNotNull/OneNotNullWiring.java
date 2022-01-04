/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/4
 * Listen  MIT
 */

package com.wuchuheng.notes.server.validators.OneNotNull;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

public class OneNotNullWiring implements ConstraintValidator<OneNotNull, Object> {
    private String [] fields;

    @Override
    public void initialize(OneNotNull constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        final BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);

        return Arrays.stream(fields).map(beanWrapper::getPropertyValue).anyMatch(Objects::nonNull);
    }
}
