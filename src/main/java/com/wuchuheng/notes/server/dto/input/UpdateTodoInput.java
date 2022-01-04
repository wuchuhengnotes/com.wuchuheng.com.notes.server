/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/1
 * Listen  MIT
 */

package com.wuchuheng.notes.server.dto.input;

import com.wuchuheng.notes.server.validators.HasTodoID.HasTodoID;
import graphql.annotations.annotationTypes.GraphQLConstructor;
import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @GraphQLConstructor)
@GraphQLDescription("The input for updating Todo.")
@Data
@Builder
public class UpdateTodoInput {
    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("The id for creating todo")
    @HasTodoID
    private Long id;

    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("The title for creating todo")
    @NotBlank(message = "The title can not be empty.")
    private String title;
}
