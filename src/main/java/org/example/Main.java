package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 20);

        StreamPipeline<Integer> pipeline = StreamPipeline.initializePipeline(data, List.class);
        pipeline.addWhereClause(x -> x > 3);
        pipeline.addWhereClause(x -> x % 2 ==0);
        pipeline.addSelectClause();

        Collection<Integer> result = pipeline.execute();

        Collection<Integer> result1 = pipeline.execute();

        System.out.println(result); // Output: [4, 5, 6]

        List<String> data1 = Arrays.asList("Alice", "Bob", "Charlie", "Amanda");

        // Initialize pipeline
        StreamPipeline<String> pipeline1 = StreamPipeline.initializePipeline(data1, List.class);

        // Add clauses
        pipeline1.addWhereClause(s -> s.startsWith("A"));
        pipeline1.addSelectClause();

        // Execute and print results
        Collection<String> results = pipeline1.execute();
        System.out.println(results); // Output: [Alice, Amanda]
    }
}
