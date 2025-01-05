package org.example;


import org.ballerinalang.jvm.types.BTupleType;
import org.ballerinalang.jvm.types.BType;
import org.ballerinalang.jvm.values.ArrayValueImpl;
import org.ballerinalang.jvm.values.TupleValueImpl;
import org.ballerinalang.jvm.values.api.BArray;
import org.ballerinalang.jvm.values.api.BCollection;
import org.example.example.BMapPipelineExample;
import org.example.pipeline.StreamPipeline;
import org.example.utils.BCollectionToStreamUtils;
import org.example.utils.StreamPipelineUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        // Input data

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 20);

        long[] values = {1L, 2L, 3L, 4L, 5L , 10L , 13L, 17L, 25L};
        BCollection collection = new ArrayValueImpl(values);  // Create an example collection

        // Initialize the pipeline
        StreamPipeline<Long> pipeline = StreamPipelineUtils.initializePipeline(collection, List.class);

        // Add where clauses to filter data
        StreamPipelineUtils.addWhereClause(pipeline, x -> x > 3); // Keep values greater than 3
        StreamPipelineUtils.addWhereClause(pipeline, x -> x % 2 == 1); // Keep even values

        // Add let clauses compute intermediate variables
        StreamPipelineUtils.addLetClause(pipeline, "square", (Long x) -> x * x); // Compute square of each element
        StreamPipelineUtils.addLetClause(pipeline, "half", (Long x) -> x / 2.0); // Compute half of each element

        // Add a limit clause to restrict results to 3
        StreamPipelineUtils.addLimitClause(pipeline, 3);
//
//        // Add a select clause to complete the pipeline
        StreamPipelineUtils.addSelectClause(pipeline);
//
//        // Execute the pipeline
        Collection<Long> result = pipeline.execute();

        // Print the let variables (computed during execution)
        System.out.println("Let Variables:");
        pipeline.getAllLetVariables().forEach((key, value) -> System.out.println(key + " = " + value));

        // Print the final results
        System.out.println("Final Results:");
        System.out.println(result);

        String[] jdata = {"Alice", "Bob", "Charlie", "Amanda"};

        BArray data1 = new ArrayValueImpl(jdata);

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

        Collection<BTupleType> result4 = BMapPipelineExample.runPipelineWithBMap();

        // Print the let variables (computed during execution)

        // Print the final results
        System.out.println("Final Results:");
        System.out.println(result4);

    }
}
