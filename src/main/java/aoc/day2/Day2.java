package aoc.day2;

import aoc.classes.intcode.IntcodeComputer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static aoc.classes.intcode.IntcodeComputer.intCodeProgram;
import static aoc.utils.InputReader.getInput;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Day2 {

    public static int GRAVITY_ASSIST_OUTPUT = 19690720;

    public static String firstValueFromIntList(List<Integer> intList) throws Exception {
        String[] values = intCodeProgram(intList).split(",");
        return values[0];
    }

    public static List<Integer> csvToIntList(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(toList());
    }

    public static String findInputParametersForGravityAssist(String input, int noun, int verb) throws Exception {
        ArrayList<Integer> memory = new ArrayList<>(csvToIntList(input));
        memory.set(1, noun);
        memory.set(2, verb);
        return firstValueFromIntList(memory);
    }

    public void day2Puzzle2() throws Exception {
        String input = getInput("/day2.txt");
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {

                String vals = findInputParametersForGravityAssist(input, i, j);
                if (vals.equals(String.valueOf(GRAVITY_ASSIST_OUTPUT))) {
                    System.out.println(format("Val: %s, Noun: %d, Verb: %d", vals, i, j));
                    System.out.println(format("100 * noun + verb is: %d ", (100 * i + j)));
                }
            }
        }
    }

}
