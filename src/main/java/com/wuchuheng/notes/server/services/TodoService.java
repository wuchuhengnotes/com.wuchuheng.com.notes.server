/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/2
 * Listen  MIT
 */

package com.wuchuheng.notes.server.services;

import com.wuchuheng.notes.server.dto.input.CreateTodoInput;
import com.wuchuheng.notes.server.dto.input.UpdateTodoInput;
import com.wuchuheng.notes.server.model.Todo;
import com.wuchuheng.notes.server.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo createTodo(@Valid CreateTodoInput input) {
        var todoRecord = this.todoRepository.save(
                Todo.builder()
                        .title(input.getTitle())
                        .isDone(false)
                        .createdAt(LocalDate.now())
                        .build()
        );

        return todoRecord;
    }

    public Todo updateTodo(@Valid UpdateTodoInput input) {
        final Todo todo = this.todoRepository.findById(input.getId().longValue());
        todo.setTitle(input.getTitle());
        this.todoRepository.save(todo);

        return todo;
    }
}
