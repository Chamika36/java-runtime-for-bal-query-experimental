package org.example;

import java.util.function.Predicate;

public class StreamPipelineUtils {

    public static <T> StreamPipeline<T> initializePipeline(Iterable<T> collection, Class<?> completionType) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection cannot be null");
        }
        FromClause<T> fromClause = new FromClause<>(collection);
        return new StreamPipeline<>(fromClause, completionType);
    }

    public static <T> void addWhereClause(StreamPipeline<T> pipeline, Predicate<T> condition) {
        WhereClause<T> whereClause = new WhereClause<>(condition);
        pipeline.addStage(whereClause);
    }

    public static <T> void addSelectClause(StreamPipeline<T> pipeline) {
        SelectClause<T> selectClause = new SelectClause<>(pipeline.getCompletionType());
        pipeline.setSelectClause(selectClause);
    }

    public static <T> void addLimitClause(StreamPipeline<T> pipeline, long limit) {
        LimitClause<T> limitClause = new LimitClause<>(limit);
        pipeline.addStage(limitClause);
    }
}
