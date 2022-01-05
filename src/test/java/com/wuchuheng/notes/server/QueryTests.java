/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2021/12/31
 * Listen  MIT
 */

package com.wuchuheng.notes.server;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.wuchuheng.notes.server.model.Todo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing the Query for GraphQL")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QueryTests extends BaseTests{
    private static final String HELLO_DATA_FIELD = "$.data.hello";

    private static final String HELLO_GRAPHQL_RESOURCE = "hello.graphql";

    @Test
    @Order(1)
    @DisplayName("Should work without parameter, returning \"Hello, World!\"")
    void testHello() throws IOException {
        log.info("Query hello.");
        final String actual = graphQLTestTemplate.postForResource(HELLO_GRAPHQL_RESOURCE).get(HELLO_DATA_FIELD);
        assertThat(actual).isEqualTo("Hello, World!");
    }

    @Test
    @Order(2)
    @DisplayName("Should work without parameter, returning set of todo.")
    void testGetTodos() throws IOException {
        super.testCreateTodo();
        final var res = graphQLTestTemplate.postForResource("todos.graphql")
                .assertThatNoErrorsArePresent()
                .assertThatField("$.data.todos")
                .and()
                .getRaw("$.data.todos");
        log.info("Show the set of response for todos: {}", res);
    }
}
