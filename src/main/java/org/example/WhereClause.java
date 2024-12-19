package org.example;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class WhereClause<T> implements PipelineStage<T> {
    private final Predicate<T> condition;

    public WhereClause(Predicate<T> condition) {
        if (condition == null) {
            throw new IllegalArgumentException("Condition cannot be null");
        }
        this.condition = condition;
    }

    @Override
    public Stream<T> apply(Stream<T> input) {
        return input.filter(condition);
    }
}
