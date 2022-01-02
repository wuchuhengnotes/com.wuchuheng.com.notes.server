/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2021/12/31
 * Listen  MIT
 */

package com.wuchuheng.notes.server.resolvers;

import com.wuchuheng.notes.server.model.Todo;
import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
@GraphQLQueryResolver
@NoArgsConstructor
public class QueryResolver {
    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("Hello world!")
    public static String hello() {
        return "Hello, World!";
    }
}
