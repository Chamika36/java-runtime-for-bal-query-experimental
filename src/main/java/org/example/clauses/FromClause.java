package org.example.clauses;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FromClause<T> implements PipelineStage<T> {
    private final Iterable<T> collection;

    public FromClause(Iterable<T> collection) {
        this.collection = collection;
    }

    @Override
    public Stream<T> apply(Stream<T> input) {
        return StreamSupport.stream(collection.spliterator(), false);
    }
}
