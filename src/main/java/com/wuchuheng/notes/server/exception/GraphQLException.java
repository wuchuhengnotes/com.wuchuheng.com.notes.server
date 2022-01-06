/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/6
 * Listen  MIT
 */

package com.wuchuheng.notes.server.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphQLException extends RuntimeException implements GraphQLError {
    public enum DEFAULT_ERROR_TYPE {
        VALIDATED_ERROR_TYPE(50001, "Validation error occurred."),
        INTERIOR_ERROR_TYPE(50000, "The error occurred inside the server."),
        GRAPHQL_ERROR_TYPE(40000, "GraphQL error occurred.");

        @Getter
        private final int errorCode;

        @Getter
        private final String errorMessage;

        DEFAULT_ERROR_TYPE(int errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
    }

    public GraphQLException(DEFAULT_ERROR_TYPE errorType) {
        errorCode = errorType.errorCode;
        this.setErrorMessage(errorType.errorMessage);
    }

    @Getter
    Integer errorCode;

    @Getter
    @Setter
    String errorMessage;

   @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
       return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        final var extensions = new HashMap<String, Object>();
        extensions.put("errorCode", this.getErrorCode());

        return extensions;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
