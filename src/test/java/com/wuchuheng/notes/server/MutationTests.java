/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/3
 * Listen  MIT
 */

package com.wuchuheng.notes.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.wuchuheng.notes.server.dto.input.CreateTodoInput;
import com.wuchuheng.notes.server.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@DisplayName("Testing the Mutation for GraphQL")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MutationTests {
    @Autowired private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired private TodoRepository todoRepository;

    @Autowired private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should create a todo.")
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
    }
}
