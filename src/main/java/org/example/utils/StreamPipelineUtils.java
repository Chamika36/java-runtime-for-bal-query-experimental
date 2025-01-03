package org.example.utils;

import org.ballerinalang.jvm.values.api.BCollection;
import org.example.clauses.*;
import org.example.pipeline.StreamPipeline;

import java.util.function.Function;
import java.util.function.Predicate;

public class StreamPipelineUtils {

    public static <T> StreamPipeline<T> initializePipeline(BCollection collection, Class<?> completionType) {
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

    public static <T> void addLetClause(StreamPipeline<T> pipeline, String variableName, Function<T, Object> expression) {
        LetClause<T> letClause = new LetClause<>(variableName, expression, pipeline);
        pipeline.addStage(letClause);
    }
}
