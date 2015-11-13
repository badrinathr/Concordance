package StreamOptions;


import com.sun.org.apache.bcel.internal.classfile.LineNumber;
import com.sun.org.apache.bcel.internal.util.SecuritySupport;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamProcessing {


    public static void main(String[] args) {
        String fileName = "./src/main/resources/file.txt" ;
        if ( args.length > 1) {
            System.err.println("Please input a single file name with fully qualified path ... " + args.length + " values entered ");
            System.exit(-1);
        }else if ( args.length == 1) {
            fileName = args[0];
        }
        StreamProcessing str = new StreamProcessing();
        str.processStreams(fileName);
    }


    public void processStreams(String fileName) {
        try (
                LineNumberReader reader = new LineNumberReader(new FileReader(fileName))
        ) {

            Map<String, List<Long>> wordCounts = retrieveWordCountMap(reader);

            wordCounts.entrySet().stream()
                    .forEach(k -> System.out.println(k.getKey() + "  {" +
                            k.getValue().size() + ":" + k.getValue().stream().map(Object::toString).collect(Collectors.joining(",")) + "}"));

        } catch (Exception e) {

            System.out.println("File processing error ... " + e);
        }
    }

    private Map<String, List<Long>> retrieveWordCountMap(LineNumberReader lr) {
            return
                lr.lines()
                        .flatMap(x -> Stream.of(x.split("\\W+")))
                        .map(x -> new WordAndLine(x.toLowerCase(), lr.getLineNumber()))
                        .collect(collectingAndThen(
                                groupingBy(WordAndLine::word,
                                        mapping(
                                                WordAndLine::line,
                                                toList())), TreeMap::new));
    }



    public class WordAndLine {
        private final String words;
        private final long line;

        public WordAndLine(String words, long line) {
            this.words = words;
            this.line = line;
        }

        public String word() {
            return words;
        }

        public long line() {
            return line;
        }

        public String toString() {
            return words + ":" + line;
        }

    }
}


