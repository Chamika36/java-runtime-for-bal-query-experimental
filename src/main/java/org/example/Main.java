package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Input data
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 20);

        // Initialize the pipeline
        StreamPipeline<Integer> pipeline = StreamPipelineUtils.initializePipeline(data, List.class);

        // Add where clauses to filter data
        StreamPipelineUtils.addWhereClause(pipeline, x -> x > 3); // Keep values greater than 3
        StreamPipelineUtils.addWhereClause(pipeline, x -> x % 2 == 0); // Keep even values

        // Add let clauses compute intermediate variables
        StreamPipelineUtils.addLetClause(pipeline, "square", (Integer x) -> x * x); // Compute square of each element
        StreamPipelineUtils.addLetClause(pipeline, "half", (Integer x) -> x / 2.0); // Compute half of each element

        // Add a limit clause to restrict results to 3
        StreamPipelineUtils.addLimitClause(pipeline, 3);

        // Add a select clause to complete the pipeline
        StreamPipelineUtils.addSelectClause(pipeline);

        // Execute the pipeline
        Collection<Integer> result = pipeline.execute();

        // Print the let variables (computed during execution)
        System.out.println("Let Variables:");
        pipeline.getAllLetVariables().forEach((key, value) -> System.out.println(key + " = " + value));

//         Print the final results
        System.out.println("Final Results:");
        result.forEach(System.out::println);

        List<String> data1 = Arrays.asList("Alice", "Bob", "Charlie", "Amanda");

        // Initialize pipeline
        StreamPipeline<String> pipeline1 = StreamPipelineUtils.initializePipeline(data1, List.class);

        // Add clauses using helper methods
        StreamPipelineUtils.addWhereClause(pipeline1, s -> s.startsWith("A"));
        StreamPipelineUtils.addLetClause(pipeline1, "Capital", (String x) -> x.toUpperCase());
        StreamPipelineUtils.addSelectClause(pipeline1);

        // Execute and print results
        System.out.println(pipeline1.execute()); // Output: [Alice, Amanda]

        System.out.println("Let Variables:");
        pipeline1.getAllLetVariables().forEach((key, value) -> System.out.println(key + " = " + value));
    }
}
