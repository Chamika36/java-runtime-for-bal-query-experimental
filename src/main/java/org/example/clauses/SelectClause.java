package org.example.clauses;

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
        if (completionType.isAssignableFrom(List.class)) {
            result = stream.collect(Collectors.toList());
        } else if (completionType.isAssignableFrom(Set.class)) {
            result = stream.collect(Collectors.toSet());
        } else {
            throw new UnsupportedOperationException(
                    "Unsupported completion type: " + completionType.getName()
            );
        }
        return Stream.empty(); // The stream is effectively consumed here.
    }

    public Collection<T> getResult() {
        return result;
    }
}
