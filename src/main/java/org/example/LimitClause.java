package org.example;

import java.util.stream.Stream;

public class LimitClause<T> implements PipelineStage<T> {
    private final long limit;

    public LimitClause(long limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Panic");
        }
        this.limit = limit;
    }

    @Override
    public Stream<T> apply(Stream<T> input) {
        return input.limit(limit);
    }
}
