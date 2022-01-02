/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2021/12/31
 * Listen  MIT
 */

package com.wuchuheng.notes.server.resolvers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

public class TimerFetcher implements DataFetcher<Publisher<Long>> {
    private final Publisher<Long> timer = Flowable.interval(1, TimeUnit.SECONDS).map(i -> i + 1);


    @Override
    public Publisher<Long> get(DataFetchingEnvironment environment) throws Exception {
        return timer;
    }
}
