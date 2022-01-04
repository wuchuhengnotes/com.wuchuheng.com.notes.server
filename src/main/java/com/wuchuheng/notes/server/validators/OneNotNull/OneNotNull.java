/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/4
 * Listen  MIT
 */

package com.wuchuheng.notes.server.validators.OneNotNull;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = OneNotNullWiring.class)
public @interface OneNotNull  {
    String message() default "Exactly least one of the fields {fields} must be set";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Fields to validate against null.
     * @return
     */
    String[] fields() default {};
}
