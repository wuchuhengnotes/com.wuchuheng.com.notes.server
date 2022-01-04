package com.wuchuheng.notes.server.validators.HasTodoID;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = HasTodoIDWiring.class)
public @interface HasTodoID {
    String message() default "The ID ${validatedValue} not found.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
