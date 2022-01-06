/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2021/12/31
 * Listen  MIT
 */

package com.wuchuheng.notes.server.services;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.reactivex.rxjava3.core.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TimerFetcher implements DataFetcher<Publisher<Long>> {
    private final Flowable<Long> timer = Flowable.interval(1, TimeUnit.SECONDS).map(i -> i + 1);

    @Override
    public Publisher<Long> get(DataFetchingEnvironment environment) throws Exception {
        return timer;
    }
}
