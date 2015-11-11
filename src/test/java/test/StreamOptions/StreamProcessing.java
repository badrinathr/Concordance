package test.StreamOptions;


import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.util.Pair;

import static java.util.stream.Collectors.*;

public class StreamProcessing {

    public static void main(String[] args) {
        StreamProcessing str = new StreamProcessing();
        str.processStreams();
    }


    private void processStreams() {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("file.txt")));
                BufferedReader br2 = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("file.txt")));
        ) {
            AtomicLong counter = new AtomicLong(0L);


            SortedMap<String, Long> wordCounts = br.lines()
                    .flatMap(x -> Stream.of(x.split("\\W+")))
                    .map(String::toLowerCase)
                    .collect(collectingAndThen(groupingBy(x -> x, counting()), TreeMap::new));


            Map<Long,String> lineNumbers = br2.lines()
                                        .collect(Collectors.toConcurrentMap( k -> counter.incrementAndGet(), v -> v  ));


            SortedMap<String, Result> result = new TreeMap<>();


//            wordCounts.entrySet().stream()
//                    .map(wordEntrySet ->
//                            lineNumbers.entrySet().stream()
//                                    .filter(x -> x.getValue().toLowerCase().contains(wordEntrySet.getKey()))
//                                    .map(x -> Long.toString(x.getKey()))
//                                    .collect(Collectors.toList()));
//
//                        result.put(word, new Result(lines, totalCount));
//                    });

//
            wordCounts.forEach(
                    (word, totalCount) -> {
                        List<String> lines = lineNumbers.entrySet().stream()
                                .filter(x -> x.getValue().toLowerCase().contains(word))
                                .map(x -> Long.toString(x.getKey()))
                                .collect(Collectors.toList());

                    result.put(word, new Result(lines, totalCount));
                    });
            System.out.println(result);


        }catch ( Exception e) {
            System.out.println("File processing error ... " + e) ;
        }
    }


    private Object sortAndJoin(List<Long> o) {
        return new ArrayList();
    }

    class Result  {

        final List<String> lineNumbers;
        final Long totalCount;

        public Result(List<String> lineNumbers, Long totalCount) {
            this.lineNumbers  = lineNumbers;
            this.totalCount = totalCount;

        }

        public String toString() {
            return "{ " + String.valueOf(totalCount) + ":" + lineNumbers.stream().collect(Collectors.joining(",")) + "}";
        }


    }

}


