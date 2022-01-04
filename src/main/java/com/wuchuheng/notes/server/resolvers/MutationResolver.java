/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/2
 * Listen  MIT
 */

package com.wuchuheng.notes.server.resolvers;

import com.wuchuheng.notes.server.dto.input.CreateTodoInput;
import com.wuchuheng.notes.server.dto.input.UpdateTodoInput;
import com.wuchuheng.notes.server.model.Todo;
import com.wuchuheng.notes.server.services.TodoService;
import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.annotations.GraphQLMutationResolver;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@GraphQLMutationResolver
@Component
@NoArgsConstructor
@Slf4j
public class MutationResolver implements ApplicationContextAware {
    private static TodoService todoService;

    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("Creating a Todo.")
    public static Todo createTodo(
            final @GraphQLNonNull CreateTodoInput input
            ) {
        return todoService.createTodo(input);
    }

    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("Updating a Todo.")
    public static Todo updateTodo( final @GraphQLNonNull UpdateTodoInput input ) {
        return todoService.updateTodo(input);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        todoService = applicationContext.getBean(TodoService.class);
    }
}
