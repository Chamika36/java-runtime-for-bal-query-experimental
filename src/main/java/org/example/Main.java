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

        System.out.println(result); // Output: [4, 5, 6]
    }
}
