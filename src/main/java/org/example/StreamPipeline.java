package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamPipeline<T> {
    private Stream<T> stream;
    private final Class<?> completionType;
    private final List<PipelineStage<T>> stages = new ArrayList<>();
    private SelectClause<T> selectClause;
    private final FromClause<T> fromClause;

    private StreamPipeline(FromClause<T> fromClause, Class<?> completionType) {
        this.fromClause = fromClause;
        this.completionType = completionType;
    }

    public static <T> StreamPipeline<T> initializePipeline(Iterable<T> collection, Class<?> completionType) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection cannot be null");
        }
        FromClause<T> fromClause = new FromClause<>(collection);
        return new StreamPipeline<>(fromClause, completionType);
    }

    public void addWhereClause(Predicate<T> condition) {
        WhereClause<T> whereClause = new WhereClause<>(condition);
        stages.add(whereClause);
    }

    public void addSelectClause() {
        selectClause = new SelectClause<>(completionType);
        stages.add(selectClause);
    }

    public Collection<T> execute() {
        // Initialize the stream using FromClause
        stream = fromClause.apply(null);

        for (PipelineStage<T> stage : stages) {
            stream = stage.apply(stream);
        }

        return selectClause != null ? selectClause.getResult() : Collections.emptyList();
    }

    public Stream<T> getStream() {
        return stream;
    }

    public Class<?> getCompletionType() {
        return completionType;
    }
}
