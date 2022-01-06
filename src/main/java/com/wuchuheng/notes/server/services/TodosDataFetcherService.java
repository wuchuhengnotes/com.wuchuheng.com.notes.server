/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/5
 * Listen  MIT
 */

package com.wuchuheng.notes.server.services;


import com.wuchuheng.notes.server.model.Todo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;

@Slf4j
@Service
public class TodosDataFetcherService implements DataFetcher<Publisher<Iterable<Todo>>> {

    private static FluxProcessor<Iterable<Todo>, Iterable<Todo>> processor;

    private static FluxSink<Iterable<Todo>> sink;

    public TodosDataFetcherService() {
        processor = DirectProcessor.<Iterable<Todo>>create().serialize();
        sink = processor.sink();
    }

    public void publish(Iterable<Todo> todo) {
        sink.next(todo);
    }

    @Override
    public Publisher<Iterable<Todo>> get(DataFetchingEnvironment environment) {
        return processor.map(todos -> {
            log.info("Publishing individual subscription update for Todo {}", todos);

            return todos;
        });
    }
}
