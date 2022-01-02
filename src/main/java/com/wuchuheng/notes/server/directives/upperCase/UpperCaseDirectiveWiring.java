/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/1
 * Listen  MIT
 */

package com.wuchuheng.notes.server.directives.upperCase;

import graphql.annotations.directives.AnnotationsDirectiveWiring;
import graphql.annotations.directives.AnnotationsWiringEnvironment;
import graphql.annotations.processor.util.CodeRegistryUtil;
import graphql.schema.*;

import java.util.Map;
import java.util.function.BiFunction;

import static graphql.annotations.processor.util.CodeRegistryUtil.getDataFetcher;

public class UpperCaseDirectiveWiring implements AnnotationsDirectiveWiring {

    @Override
    public GraphQLInputObjectField onInputObjectField(AnnotationsWiringEnvironment environment) {
        final var field = (GraphQLInputObjectField) environment.getElement();
        String parentName = ((GraphQLNamedSchemaElement) environment.getParentElement()).getName();

        DataFetcher authDataFetcher = new DataFetcher() {
            @Override
            public Object get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
                return null;
            }
        };
        environment
                .getCodeRegistryBuilder()
                .dataFetcher(FieldCoordinates.coordinates(parentName, field.getName()), authDataFetcher);

        return field;
    }

    @Override
    public GraphQLFieldDefinition onField(final AnnotationsWiringEnvironment environment) {
        final GraphQLFieldDefinition field = (GraphQLFieldDefinition) environment.getElement();
        CodeRegistryUtil.wrapDataFetcher(
                field,
                environment,
                (((dataFetchingEnvironment, value) -> {
                    if (value instanceof String) {
                        return ((String) value).toUpperCase();
                    }
                    return value;
                })));
        return field;
    }
}
