/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2021/12/31
 * Listen  MIT
 */

package com.wuchuheng.notes.server.resolvers;

import com.wuchuheng.notes.server.model.Todo;
import com.wuchuheng.notes.server.repository.TodoRepository;
import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Slf4j
@GraphQLQueryResolver
@NoArgsConstructor
@Component
public class QueryResolver implements ApplicationContextAware {
    private static TodoRepository todoRepository;

    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("Hello world!")
    public static String hello() {
        return "Hello, World!";
    }

    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("Returns all todos.")
    public static Iterable<Todo> todos() {
        return todoRepository.findAllNotDoneTodo();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        todoRepository = applicationContext.getBean(TodoRepository.class);
    }
}
