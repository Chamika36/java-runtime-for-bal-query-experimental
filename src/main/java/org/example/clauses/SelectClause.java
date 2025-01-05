package org.example.clauses;

import org.ballerinalang.jvm.values.api.BCollection;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelectClause<T> implements PipelineStage<T> {
    private final Class<?> completionType;
    private Collection<T> result;

    public SelectClause(Class<?> completionType) {
        this.completionType = completionType;
    }

    @Override
    public Stream<T> apply(Stream<T> stream) {
        if (stream == null) {
            throw new IllegalStateException("Stream is null. Check the pipeline stages.");
        }

        try {
            if (completionType.isAssignableFrom(List.class)) {
                result = stream.collect(Collectors.toList());
            } else if (completionType.isAssignableFrom(Set.class)) {
                result = stream.collect(Collectors.toSet());
            } else if (completionType.isAssignableFrom(BCollection.class)) {
                result = (Collection<T>) stream.collect(Collectors.toList());
            } else {
                throw new UnsupportedOperationException(
                        "Unsupported completion type: " + completionType.getName()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during SelectClause execution.", e);
        }

        return Stream.empty(); // Stream is consumed here.
    }

    public Collection<T> getResult() {
        if (result == null) {
            throw new IllegalStateException("SelectClause result is null. Ensure apply() was called.");
        }
        return result;
    }
}
