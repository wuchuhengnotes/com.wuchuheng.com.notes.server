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
import com.wuchuheng.notes.server.dto.input.UpdateTodoInput;
import com.wuchuheng.notes.server.model.Todo;
import com.wuchuheng.notes.server.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing the Mutation for GraphQL")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MutationTests {
    @Autowired private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired private TodoRepository todoRepository;

    @Autowired private ObjectMapper objectMapper;

    private static Long newTodoRecordId;

    @Test
    @Order(1)
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
        newTodoRecordId = Long.valueOf(id);
    }

    @Test
    @Order(2)
    @DisplayName("Should update a todo.")
    void testUpdateTodo() throws IOException {
        final var input = UpdateTodoInput.builder().title("1234").id(newTodoRecordId).done(true).build();
        final var params = objectMapper.createObjectNode();
        // THEN - assert the response of GraphQL.
        params.set("input", objectMapper.valueToTree(input));
        final var res = graphQLTestTemplate.perform("updateTodo.graphql", params)
                .assertThatNoErrorsArePresent()
                .assertThatField("$.data.updateTodo.id").asInteger().isEqualTo(input.getId().longValue())
                .and()
                .assertThatField("$.data.updateTodo.title").as(String.class).isEqualTo(input.getTitle())
                .and()
                .assertThatField("$.data.updateTodo.done").asBoolean().isEqualTo(input.getDone())
                .and()
                .getRawResponse()
                .toString();
        log.info("Update the todo Then get the response: {}", res);
        // THEN - assert the effect of persistence.
        final Todo todoRecord = todoRepository.findById(input.getId().longValue());
        assertThat(todoRecord.getTitle()).isEqualTo(input.getTitle());
    }
}
