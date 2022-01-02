/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2021/12/31
 * Listen  MIT
 */

package com.wuchuheng.notes.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wuchuheng.notes.server.directives.upperCase.UpperCaseDirective;
import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GraphQLField
    @GraphQLNonNull
    private Long id;

    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("the title of Todo.")
    private String title;

    @GraphQLField
    @GraphQLNonNull
    @NotNull
    private Boolean isDone;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @JsonIgnore
    @GraphQLDescription("Creation time.")
    @GraphQLNonNull
    @GraphQLField
    private LocalDate createdAt;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @JsonIgnore
    @GraphQLField
    @GraphQLDescription("Completion time.")
    private LocalDate doneAt;
}
