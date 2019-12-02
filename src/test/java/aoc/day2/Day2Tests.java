package aoc.day2;

import org.junit.Test;

import static aoc.day2.Day2.*;
import static aoc.utils.InputReader.getInput;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Day2Tests {
    @Test
    public void testAOCDay2Examples() throws Exception {
        assertThat(intCodeProgram("1,0,0,0,99"), equalTo("2,0,0,0,99"));
        assertThat(intCodeProgram("2,3,0,3,99"), equalTo("2,3,0,6,99"));
        assertThat(intCodeProgram("2,4,4,5,99,0"), equalTo("2,4,4,5,99,9801"));
        assertThat(intCodeProgram("1,1,1,4,99,5,6,0,99"), equalTo("30,1,1,4,2,5,6,0,99"));
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
}
