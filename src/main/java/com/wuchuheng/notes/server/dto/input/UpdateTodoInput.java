/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/1
 * Listen  MIT
 */

package com.wuchuheng.notes.server.dto.input;

import com.wuchuheng.notes.server.validators.HasTodoID.HasTodoID;
import com.wuchuheng.notes.server.validators.OneNotNull.OneNotNull;
import graphql.annotations.annotationTypes.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @GraphQLConstructor)
@GraphQLDescription("The input for updating Todo.")
@Data
@Builder
@OneNotNull(fields = {"title", "done"})
public class UpdateTodoInput {
    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("The id for creating todo")
    @HasTodoID
    private Long id;

    @GraphQLField
    @GraphQLDescription("The title for creating todo")
    @Length(min = 1, message = "The length of title must be at least one ")
    private String title;

    @GraphQLField
    @GraphQLDescription("Mark if done.")
    private Boolean done;
}
