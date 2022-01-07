/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/2
 * Listen  MIT
 */

package com.wuchuheng.notes.server.repository;

import com.wuchuheng.notes.server.model.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    Todo findById(long id);

    @Query(
            value = "SELECT * FROM todo u WHERE u.is_done = false",
            nativeQuery = true)
    Iterable<Todo> findAllNotDoneTodo();
}
