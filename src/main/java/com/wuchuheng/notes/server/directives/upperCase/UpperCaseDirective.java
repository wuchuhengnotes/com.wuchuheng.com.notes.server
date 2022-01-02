/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/1
 * Listen  MIT
 */

package com.wuchuheng.notes.server.directives.upperCase;

import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.annotations.annotationTypes.directives.definition.DirectiveLocations;
import graphql.annotations.annotationTypes.directives.definition.GraphQLDirectiveDefinition;
import graphql.introspection.Introspection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@GraphQLName("upperCase")
@GraphQLDescription("This directive makes a string uppercase")
@GraphQLDirectiveDefinition(wiring = UpperCaseDirectiveWiring.class)
@DirectiveLocations({
        Introspection.DirectiveLocation.INPUT_FIELD_DEFINITION,
        Introspection.DirectiveLocation.QUERY,
        Introspection.DirectiveLocation.MUTATION,
        Introspection.DirectiveLocation.SUBSCRIPTION,
        Introspection.DirectiveLocation.FIELD,
        Introspection.DirectiveLocation.FRAGMENT_DEFINITION,
        Introspection.DirectiveLocation.FRAGMENT_SPREAD,
        Introspection.DirectiveLocation.INLINE_FRAGMENT,
        //
        // schema SDL places
        //
        Introspection.DirectiveLocation.SCHEMA,
        Introspection.DirectiveLocation.SCALAR,
        Introspection.DirectiveLocation.OBJECT,
        Introspection.DirectiveLocation.FIELD_DEFINITION,
        Introspection.DirectiveLocation.ARGUMENT_DEFINITION,
        Introspection.DirectiveLocation.INTERFACE,
        Introspection.DirectiveLocation.UNION,
        Introspection.DirectiveLocation.INPUT_OBJECT,
        Introspection.DirectiveLocation.INPUT_FIELD_DEFINITION
})
@Retention(RetentionPolicy.RUNTIME)
public @interface UpperCaseDirective {
    @GraphQLName("suffixToAdd")
    @GraphQLDescription("the suffix to add to your type")
    boolean suffixToAdd() default true;
}
