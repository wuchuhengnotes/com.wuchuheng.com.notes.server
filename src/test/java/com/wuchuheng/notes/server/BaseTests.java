/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/5
 * Listen  MIT
 */

package com.wuchuheng.notes.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.wuchuheng.notes.server.dto.input.CreateTodoInput;
import com.wuchuheng.notes.server.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public abstract class BaseTests {
    public static Long newTodoRecordId;

    @Autowired protected GraphQLTestTemplate graphQLTestTemplate;

    @Autowired protected TodoRepository todoRepository;

    @Autowired protected ObjectMapper objectMapper;

    void testCreateTodo() throws IOException {
        final ObjectNode params = objectMapper.createObjectNode();
        final var title = "hello";
        final var input = CreateTodoInput.builder()
                .title(title)
                .build();
        params.set("input", objectMapper.valueToTree(input));
        log.info("Create Todo variables: {}", params);
        var id = graphQLTestTemplate.perform("createTodo.graphql", params)
                .assertThatNoErrorsArePresent()
                .assertThatField("$.data.createTodo.title")
                .as(String.class)
                .isEqualTo(title)
                .and()
                .get("$.data.createTodo.id");
        var persistenceTodo = this.todoRepository.findById(Long.valueOf(id));
        assertThat(persistenceTodo.get().getId())
                .as("Should create only one todo.")
                .isEqualTo(Long.valueOf(id));
        newTodoRecordId = Long.valueOf(id);
    }
}
