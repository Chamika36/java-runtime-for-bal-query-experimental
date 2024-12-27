package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 20);

        StreamPipeline<Integer> pipeline = StreamPipelineUtils.initializePipeline(data, List.class);
        StreamPipelineUtils.addWhereClause(pipeline, x -> x > 3);
        StreamPipelineUtils.addWhereClause(pipeline, x -> x % 2 == 0);
        StreamPipelineUtils.addSelectClause(pipeline);

        Collection<Integer> result = pipeline.execute();

        Collection<Integer> result1 = pipeline.execute();

        System.out.println(result);

        List<String> data1 = Arrays.asList("Alice", "Bob", "Charlie", "Amanda");

        // Initialize pipeline
        StreamPipeline<String> pipeline1 = StreamPipelineUtils.initializePipeline(data1, List.class);

        // Add clauses using helper methods
        StreamPipelineUtils.addWhereClause(pipeline1, s -> s.startsWith("A"));
        StreamPipelineUtils.addSelectClause(pipeline1);

        // Execute and print results
        System.out.println(pipeline1.execute()); // Output: [Alice, Amanda]
    }
}
