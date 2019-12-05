package aoc.day2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static aoc.classes.intcode.IntcodeComputer.intCodeProgram;
import static aoc.classes.intcode.IntcodeComputer.runIntcodeProgram;
import static aoc.day2.Day2.*;
import static aoc.utils.InputReader.getInput;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Day2Tests {
    @Test
    public void testAOCDay2Examples() throws Exception {
        assertIntcode(Arrays.asList(1,0,0,0,99), List.of(2,0,0,0,99));
        assertIntcode(Arrays.asList(2,3,0,3,99), List.of(2,3,0,6,99));
        assertIntcode(Arrays.asList(2,4,4,5,99,0), List.of(2,4,4,5,99,9801));
        assertIntcode(Arrays.asList(1,1,1,4,99,5,6,0,99), List.of(30,1,1,4,2,5,6,0,99));
    }

    @Test
    public void testDay2Puzzle1() throws Exception {
        assertThat(findInputParametersForGravityAssist(getInput("/day2.txt"), 12, 2),
                equalTo("5290681"));
    }

    @Test
    public void testDay2Puzzle2() throws Exception {
        assertThat(findInputParametersForGravityAssist(getInput("/day2.txt"),57,41),
                equalTo(String.valueOf(GRAVITY_ASSIST_OUTPUT)));
    }

    private void assertIntcode(List<Integer> question, List<Integer> answer) {
        runIntcodeProgram(question);
        assertEquals(question, answer);
    }
}
