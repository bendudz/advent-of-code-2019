package aoc.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class InputReader {
    public static String getInput(String file) throws IOException {
        InputStream stream = InputReader.class.getResourceAsStream(file);
        return stream != null
                ? IOUtils.toString(InputReader.class.getResourceAsStream(file), StandardCharsets.UTF_8)
                : null;
    }

    public static Stream<Integer> getInputAsStream(String file) throws IOException {
        String input = getInput(file);
        return input != null ? new BufferedReader(new StringReader(input))
                .lines()
                .map(Integer::valueOf)
                : null;
    }
}
