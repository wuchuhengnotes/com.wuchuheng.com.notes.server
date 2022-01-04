/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/3
 * Listen  MIT
 */

package com.wuchuheng.notes.server.validators.HasTodoID;

import com.wuchuheng.notes.server.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@RequiredArgsConstructor
public class HasTodoIDWiring implements ConstraintValidator<HasTodoID, Long> {
    private final TodoRepository todoRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return this.todoRepository.existsById(id);
    }
}
