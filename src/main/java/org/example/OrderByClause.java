package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class OrderByClause<T> implements PipelineStage<T> {
    private final List<OrderKey<T>> orderKeys = new ArrayList<>();

    // Add a key and its order direction (true for ascending, false for descending)
    public void addOrderKey(Comparator<T> keyExtractor, boolean ascending) {
        orderKeys.add(new OrderKey<>(keyExtractor, ascending));
    }

    @Override
    public Stream<T> apply(Stream<T> input) {
        Comparator<T> combinedComparator = null;

        for (OrderKey<T> orderKey : orderKeys) {
            Comparator<T> keyComparator = orderKey.ascending ? orderKey.keyExtractor : orderKey.keyExtractor.reversed();

            if (combinedComparator == null) {
                combinedComparator = keyComparator;
            } else {
                combinedComparator = combinedComparator.thenComparing(keyComparator);
            }
        }

        if (combinedComparator != null) {
            return input.sorted(combinedComparator);
        }
        return input;
    }

    private record OrderKey<T>(Comparator<T> keyExtractor, boolean ascending) {
    }
}
