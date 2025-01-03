package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class StreamPipeline<T> {
    private Stream<T> stream;
    private final Class<?> completionType;
    private final List<PipelineStage<T>> stages = new ArrayList<>();
    private final FromClause<T> fromClause;

    private SelectClause<T> selectClause;

    public StreamPipeline(FromClause<T> fromClause, Class<?> completionType) {
        this.fromClause = fromClause;
        this.completionType = completionType;
    }

    public void addStage(PipelineStage<T> stage) {
        stages.add(stage);
    }

    public Collection<T> execute() {
        // Initialize the stream using FromClause
        stream = fromClause.apply(null);

        for (PipelineStage<T> stage : stages) {
            stream = stage.apply(stream);
        }

        return selectClause != null ? selectClause.getResult() : Collections.emptyList();
    }

    public void setSelectClause(SelectClause<T> selectClause) {
        this.selectClause = selectClause;
        stages.add(selectClause);
    }

    public Stream<T> getStream() {
        return stream;
    }

    public Class<?> getCompletionType() {
        return completionType;
    }
}
