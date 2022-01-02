/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2021/12/31
 * Listen  MIT
 */

package com.wuchuheng.notes.server;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing the Query for GraphQL")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QueryTests {
    private static final String HELLO_DATA_FIELD = "$.data.hello";
    private static final String HELLO_GRAPHQL_RESOURCE = "hello.graphql";

    @Autowired private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    @DisplayName("Should work without parameter, returning \"Hello, World!\"")
    void testHello() throws IOException {
        final String actual = graphQLTestTemplate.postForResource(HELLO_GRAPHQL_RESOURCE)
                .get(HELLO_DATA_FIELD);
        assertThat(actual).isEqualTo("Hello, World!");
    }
}
