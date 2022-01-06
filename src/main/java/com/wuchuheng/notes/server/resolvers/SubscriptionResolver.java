/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2021/12/31
 * Listen  MIT
 */

package com.wuchuheng.notes.server.resolvers;


import com.wuchuheng.notes.server.model.Todo;
import com.wuchuheng.notes.server.services.TimerFetcher;
import com.wuchuheng.notes.server.services.TodosDataFetcherService;
import graphql.annotations.annotationTypes.GraphQLDataFetcher;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.annotations.GraphQLSubscriptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@GraphQLSubscriptionResolver
@Slf4j
public class SubscriptionResolver {
    @GraphQLField
    @GraphQLNonNull
    @GraphQLDataFetcher(TimerFetcher.class)
    public static Long timer() {
        log.info("This method will never be called.");
        return null;
    }

    @GraphQLField
    @GraphQLNonNull
    @GraphQLDataFetcher(TodosDataFetcherService.class)
    public static Iterable<Todo> todos() {
        return null;
    }

}
