/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/6
 * Listen  MIT
 */

package com.wuchuheng.notes.server.config.globalExceptionHandler;

import com.wuchuheng.notes.server.exception.GraphQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ConstraintViolationException.class})
    public GraphQLException handle(ConstraintViolationException e) {
        final GraphQLException err = new GraphQLException(GraphQLException.DEFAULT_ERROR_TYPE.VALIDATED_ERROR_TYPE);
        err.setErrorMessage(e.getMessage());

        return err;
    }

    @ExceptionHandler({RuntimeException.class})
    public GraphQLException handle(RuntimeException e) {
        try {
            final var causeException= e.getCause().getCause();
            if (causeException instanceof ConstraintViolationException) {
                return this.handle((ConstraintViolationException)causeException);
            }
            if (causeException instanceof GraphQLException) {
                return this.handle((GraphQLException)causeException);
            }
        }catch (Exception ignored) { }
        final var err = new GraphQLException(GraphQLException.DEFAULT_ERROR_TYPE.INTERIOR_ERROR_TYPE);
        err.setErrorMessage(e.getMessage());

        return  err;
    }

    @ExceptionHandler({GraphQLException.class})
    public GraphQLException handle(GraphQLException e) {
        final GraphQLException err = new GraphQLException(GraphQLException.DEFAULT_ERROR_TYPE.GRAPHQL_ERROR_TYPE);
        err.setErrorMessage(e.getMessage());

        return err;
    }
}
