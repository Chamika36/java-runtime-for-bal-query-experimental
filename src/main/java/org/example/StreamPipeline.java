package org.example;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamPipeline<T> {
    private Stream<T> stream;
    private final Class<?> completionType;
    private final List<PipelineStage<T>> stages = new ArrayList<>();
    private SelectClause<T> selectClause;

    public StreamPipeline(Stream<T> stream, Class<?> completionType) {
        this.stream = stream;
        this.completionType = completionType;
    }

    public static <T> StreamPipeline<T> initializePipeline(Iterable<T> collection, Class<?> completionType) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection cannot be null");
        }
        return new StreamPipeline<>(StreamSupport.stream(collection.spliterator(), false), completionType);
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
