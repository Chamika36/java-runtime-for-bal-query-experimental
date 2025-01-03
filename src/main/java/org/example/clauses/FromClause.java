package org.example.clauses;

import org.ballerinalang.jvm.values.api.BCollection;
import org.example.utils.BCollectionToStreamUtils;

import java.util.stream.Stream;

/**
 * Clause that applies the `from` operation to the stream, now supporting BCollection.
 */
public class FromClause<T> implements PipelineStage<T> {
    private final BCollection collection;
    private String variableName;

    public FromClause(BCollection collection) {
        this.collection = collection;
    }

    @Override
    public Stream<T> apply(Stream<T> input) {
        // Use the utility to convert BCollection to a Java Stream
        return BCollectionToStreamUtils.toStream(collection);
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }
}
