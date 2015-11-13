package test.StreamOptions;

import StreamOptions.StreamProcessing;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.LineNumberReader;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class StreamProcessorTest {

    final static String FOLDER = "./src/test/resources/";
    StreamProcessing processor;
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    LineNumberReader inputFile;

    @Before
    public void setup() {
        processor = new StreamProcessing();
        System.setOut(new PrintStream(output));

    }

    @Test
    public void checkResultForFileWithSingleLineAndUniqueContents() {
        String expectedResult = "a  {1:1}\n" +
                "is  {1:1}\n" +
                "line  {1:1}\n" +
                "single  {1:1}\n" +
                "this  {1:1}\n" +
                "unique  {1:1}\n" +
                "with  {1:1}\n" +
                "words  {1:1}\n";
        processor.processStreams(FOLDER + "fileWithSingleLineAnd_8_UniqueWords.txt");
        assertEquals(expectedResult, output.toString());

    }

    @Test
    public  void checkResultForFileWithSingleLineAndNonUniqueContents(){
        String expectedResult = "a  {2:1,1}\n" +
                "is  {2:1,1}\n" +
                "line  {1:1}\n" +
                "single  {1:1}\n" +
                "this  {1:1}\n" +
                "unique  {1:1}\n" +
                "with  {1:1}\n" +
                "words  {3:1,1,1}\n";
        processor.processStreams(FOLDER + "fileWithSingleLineAndNonUniqueWords.txt");
        assertEquals(expectedResult, output.toString());


    }


    @Test
    public  void checkResultForFileWithManyLinesAndNonUniqueContents(){
        String expectedResult = "a  {2:1,1}\n" +
                "is  {2:1,3}\n" +
                "line  {1:1}\n" +
                "single  {1:1}\n" +
                "this  {1:1}\n" +
                "unique  {1:1}\n" +
                "with  {1:1}\n" +
                "words  {3:1,2,3}\n";
        processor.processStreams(FOLDER + "fileWithManyLinesAndNonUniqueWords.txt");
        assertEquals(expectedResult, output.toString());


    }

    @After
    public void cleanup() {
        System.setOut(null);
    }

}
