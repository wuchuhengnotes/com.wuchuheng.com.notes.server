/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/1
 * Listen  MIT
 */

package com.wuchuheng.notes.server.dto.input;

import com.wuchuheng.notes.server.directives.upperCase.UpperCaseDirective;
import graphql.annotations.annotationTypes.GraphQLConstructor;
import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @GraphQLConstructor)
@GraphQLDescription("The input for creating Todo.")
@Setter
public class CreateTodoInput {
    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("the creating title of Todo.")
    @UpperCaseDirective(suffixToAdd = true)
    @NotBlank(message = "The title can not be empty.")
    private String title;
}
