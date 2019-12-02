package aoc.day2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static aoc.utils.InputReader.getInput;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Day2 {

    public static int GRAVITY_ASSIST_OUTPUT = 19690720;

    public static String intCodeProgram(List<Integer> memory) throws Exception {
        int address = 0;
        int result = 0;
        int resultPos = 0;

        while (memory.get(address) != 99) {
            int parameter1 = memory.get(address + 1);
            int parameter2 = memory.get(address + 2);
            switch (memory.get(address)) {
                case 1:
                    result = memory.get(parameter1) + memory.get(parameter2);
                    resultPos = memory.get(address + 3);
                    break;
                case 2:
                    result = memory.get(parameter1) * memory.get(parameter2);
                    resultPos = memory.get(address + 3);
                    break;
                case 99:
                    break;
                default:
                    throw new Exception(format("Expected 1,2 or 99; found %d", memory.get(0)));
            }
            memory.set(resultPos, result);
            address += 4;
        }
        return memory.stream().map(String::valueOf).collect(Collectors.joining(","));

    }

    public static String intCodeProgram(String intCode) throws Exception {
        ArrayList<Integer> memory = new ArrayList<>(parseInput(intCode));
        return intCodeProgram(memory);
    }

    public static String firstValueFromIntList(List<Integer> intList) throws Exception {
        String input = intList.stream().map(String::valueOf).collect(Collectors.joining(","));
        String[] values = intCodeProgram(intList).split(",");
        return values[0];
    }

    private static List<Integer> parseInput(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(toList());
    }

    public static String findInputParametersForGravityAssist(String input, int noun, int verb) throws Exception {
        ArrayList<Integer> memory = new ArrayList<>(parseInput(input));
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
