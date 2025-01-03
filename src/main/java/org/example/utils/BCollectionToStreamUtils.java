package org.example.utils;


import org.ballerinalang.jvm.values.api.BCollection;
import org.ballerinalang.jvm.values.api.BIterator;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Utility class to convert BCollection to Java Stream.
 * This is a generic class to handle any type of BCollection.
 */
public class BCollectionToStreamUtils {

    /**
     * Converts any BCollection to a Java Stream.
     *
     * @param collection The BCollection to convert
     * @param <T> The type of elements in the BCollection
     * @return A Java Stream of elements from the BCollection
     */
    public static <T> Stream<T> toStream(BCollection collection) {
        // Get the iterator from the BCollection
        BIterator<?> iterator = collection.getIterator();

        // Create a Java iterator from the BIterator
        Iterator<T> javaIterator = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();  // Check if there are more elements
            }

            @Override
            public T next() {
                return (T) iterator.next();  // Get the next element from the iterator
            }
        };

        // Return the stream created from the Java iterator
        return StreamSupport.stream(((Iterable<T>) () -> javaIterator).spliterator(), false);
    }
}
