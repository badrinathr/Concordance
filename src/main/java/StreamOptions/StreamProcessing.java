package StreamOptions;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamProcessing {

    public static void main(String[] args) {
        StreamProcessing str = new StreamProcessing();
        str.processStreams();
    }


    private void processStreams() {
        try (
                LineNumberReader lr = new LineNumberReader(new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("file.txt"))))
        ) {
            Map<String, List<Long>> wordCounts =
                    lr.lines()
                            .flatMap(x -> Stream.of(x.split("\\W+")))
                            .map(x -> new WordAndLine(x.toLowerCase(), lr.getLineNumber()))
                            .collect(collectingAndThen(
                                    groupingBy(WordAndLine::word,
                                            mapping(
                                                    WordAndLine::line,
                                                    toList())), TreeMap::new));

            wordCounts.entrySet().stream()
                    .forEach(k -> System.out.println("{" + k.getKey() + " : " +
                            k.getValue().size() + k.getValue().stream().collect(collectingAndThen(toSet(), TreeSet::new)) + "}"));

        } catch (Exception e) {

            System.out.println("File processing error ... " + e);
        }
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


