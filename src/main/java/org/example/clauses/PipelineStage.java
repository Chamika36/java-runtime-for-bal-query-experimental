package org.example.clauses;

import java.util.stream.Stream;

public interface PipelineStage<T> {
    /**
     * Applies the processing logic of this stage to the given input stream.
     *
     * @param input The input stream to process.
     * @return The transformed output stream.
     */
    Stream<T> apply(Stream<T> input);
}
