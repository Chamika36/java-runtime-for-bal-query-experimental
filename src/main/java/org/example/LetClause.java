package org.example;

import java.util.function.Function;
import java.util.stream.Stream;

public class LetClause<T> implements PipelineStage<T> {
    private final String variableName;
    private final Function<T, Object> expression;
    private final StreamPipeline<T> pipeline;

    public LetClause(String variableName, Function<T, Object> expression, StreamPipeline<T> pipeline) {
        if (variableName == null || variableName.isEmpty()) {
            throw new IllegalArgumentException("Variable name cannot be null or empty");
        }
        if (expression == null) {
            throw new IllegalArgumentException("Expression cannot be null");
        }
        if (pipeline == null) {
            throw new IllegalArgumentException("Pipeline cannot be null");
        }

        this.variableName = variableName;
        this.expression = expression;
        this.pipeline = pipeline;
    }

    @Override
    public Stream<T> apply(Stream<T> input) {
        return input.peek(element -> {
            Object value = expression.apply(element);
            pipeline.addLetVariable(variableName, value);
        });
    }

}
