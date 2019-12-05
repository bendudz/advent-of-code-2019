package aoc.day5;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

import static aoc.classes.intcode.IntcodeComputer.*;
import static aoc.utils.InputReader.getInput;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Day5Tests {

    @Test
    public void testInputExamplePart1() {
        IntStream.range(-10, 10).forEach(n -> {
            List<Integer> instruction = Arrays.asList(3, 1, 99);
            Stack<Integer> in = new Stack<>();
            in.push(n);
            runIntcodeProgram(instruction, in, new Stack<>());
            assertEquals((int) instruction.get(1), n);
        });
    }

    @Test
    public void testOutputExamplePart1() {
        IntStream.range(-10, 10).forEach(n -> {
            List<Integer> instruction = Arrays.asList(4, 3, 99, n);
            Stack<Integer> out = new Stack<>();
            runIntcodeProgram(instruction, new Stack<>(), out);
            assertEquals((int) out.pop(), n);
        });
    }

    @Test
    public void testInputAndOutputExamplePart1() {
        IntStream.range(-10, 10).forEach(n -> {
            List<Integer> instruction = Arrays.asList(3, 0, 4, 0, 99);
            Stack<Integer> in = new Stack<>();
            Stack<Integer> out = new Stack<>();
            in.push(n);
            runIntcodeProgram(instruction, in, out);
            assertEquals((int) out.pop(), n);
        });
    }

    @Test
    public void testPart1Example() {
        List<Integer> instruction = Arrays.asList(1002, 4, 3, 4, 33);
        runIntcodeProgram(instruction, new Stack<>(), new Stack<>());
        assertEquals(instruction, List.of(1002, 4, 3, 4, 99));
    }

    @Test
    public void solvePart1Puzzle() throws IOException {
        Stack<Integer> in = new Stack<>();
        Stack<Integer> out = new Stack<>();
        in.push(1);
        runIntcodeProgram(stringToIntcode(getInput("/day5.txt")), in, out);
        assertThat(out.pop(), equalTo(5182797));
    }

    @Test
    public void testPart2Example1() {
        IntStream.range(-10, 20).forEach(n -> {
            List<Integer> instruction = Arrays.asList(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8);
            var in = new Stack<Integer>();
            in.push(n);
            var output = new Stack<Integer>();
            runIntcodeProgram(instruction, in, output);
            assertEquals((int) output.pop(), n == 8 ? 1 : 0);
        });
    }

    @Test
    public void testPart2Example2() {
        IntStream.range(-10, 20).forEach(n -> {
            List<Integer> instruction = Arrays.asList(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8);
            var in = new Stack<Integer>();
            in.push(n);
            var output = new Stack<Integer>();
            runIntcodeProgram(instruction, in, output);
            assertEquals((int) output.pop(), n < 8 ? 1 : 0);
        });
    }

    @Test
    public void testPart2Example3() {
        IntStream.range(-10, 20).forEach(n -> {
            List<Integer> instruction = Arrays.asList(3, 3, 1108, -1, 8, 3, 4, 3, 99);
            var in = new Stack<Integer>();
            in.push(n);
            var output = new Stack<Integer>();
            runIntcodeProgram(instruction, in, output);
            assertEquals((int) output.pop(), n == 8 ? 1 : 0);
        });
    }

    @Test
    public void testPart2Example4() {
        IntStream.range(-10, 20).forEach(n -> {
            List<Integer> instruction = Arrays.asList(3, 3, 1107, -1, 8, 3, 4, 3, 99);
            var in = new Stack<Integer>();
            in.push(n);
            var output = new Stack<Integer>();
            runIntcodeProgram(instruction, in, output);
            assertEquals((int) output.pop(), n < 8 ? 1 : 0);
        });
    }

    @Test
    public void testPart2Example5() {
        IntStream.range(-10, 20).forEach(n -> {
            List<Integer> instruction = Arrays.asList(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9);
            var in = new Stack<Integer>();
            in.push(n);
            var output = new Stack<Integer>();
            runIntcodeProgram(instruction, in, output);
            assertEquals((int) output.pop(), n == 0 ? 0 : 1);
        });
    }

    @Test
    public void testPart2Example6() {
        IntStream.range(-10, 20).forEach(n -> {
            List<Integer> instruction = Arrays.asList(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1);
            var in = new Stack<Integer>();
            in.push(n);
            var output = new Stack<Integer>();
            runIntcodeProgram(instruction, in, output);
            assertEquals((int) output.pop(), n == 0 ? 0 : 1);
        });
    }

    @Test
    public void testPart2Example7() {
        IntStream.range(-10, 20).forEach(n -> {
            List<Integer> instruction = Arrays.asList(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                    1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                    999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99);
            var in = new Stack<Integer>();
            in.push(n);
            var output = new Stack<Integer>();
            runIntcodeProgram(instruction, in, output);
            assertEquals((int) output.pop(), n < 8 ? 999 : (n==8 ? 1000 : 1001));
        });
    }

    @Test
    public void solvePart2Puzzle() throws IOException {
        Stack<Integer> in = new Stack<>();
        Stack<Integer> out = new Stack<>();
        in.push(5);
        runIntcodeProgram(stringToIntcode(getInput("/day5.txt")), in, out);
        assertThat(out.pop(), equalTo(12077198));
    }

}
