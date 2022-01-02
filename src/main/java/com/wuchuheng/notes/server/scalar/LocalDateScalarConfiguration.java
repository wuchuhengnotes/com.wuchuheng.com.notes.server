/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/2
 * Listen  MIT
 */

package com.wuchuheng.notes.server.scalar;

import graphql.language.StringValue;
import graphql.schema.*;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Configuration
@NoArgsConstructor
public class LocalDateScalarConfiguration {
    @Bean
    public GraphQLScalarType localDateScalar() {
        return GraphQLScalarType
                .newScalar()
                .name("Date")
                .description("LocalDate as GraphQL Scalar.")
                .coercing(
                        new Coercing() {
                            @Override
                            public Object serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
                                if (!(dataFetcherResult instanceof LocalDate)) {
                                    throw new CoercingSerializeException("LocalDate expected.");
                                }
                                return String.valueOf(dataFetcherResult);
                            }

                            @NotNull
                            @Override
                            public Object parseValue(@NotNull Object input) throws CoercingParseValueException {
                                try {
                                    return LocalDate.parse(String.valueOf(input));
                                } catch (DateTimeParseException e) {
                                    return new CoercingParseValueException(e);
                                }
                            }

                            @NotNull
                            @Override
                            public Object parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
                                try {
                                    if (!(input instanceof StringValue)) {
                                        throw new CoercingParseLiteralException("String value expected.");
                                    }
                                    return LocalDate.parse(((StringValue) input).getValue());
                                } catch (DateTimeParseException e) {
                                   throw new CoercingParseLiteralException(e);
                                }
                            }
                        }
                )
                .build();
    }
}
