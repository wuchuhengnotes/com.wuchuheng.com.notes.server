/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/1
 * Listen  MIT
 */

package com.wuchuheng.notes.server;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestSubscription;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Testing the Subscription")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SubscriptionTests {
    private static final String TIMER_GRAPHQL_RESOURCE = "timer.graphql";
    private static final String TIMER_DATA_FIELD = "$.data.timer";

    @Autowired private GraphQLTestSubscription graphQLTestSubscription;

    @Test
    @DisplayName("Shout return the number of elapsed seconds since subscription started.")
    void testTimerSubscription() {
        final var timeout = Duration.ofMillis(2900);
        final List<GraphQLResponse> graphQLResponseList = graphQLTestSubscription
                .start(TIMER_GRAPHQL_RESOURCE).awaitAndGetNextResponses(timeout, 2);
        assertThat(graphQLResponseList)
                .extracting(graphQLResponse -> graphQLResponse.get(TIMER_DATA_FIELD, Long.class))
                .containsExactly(1L, 2L);
    }
}
