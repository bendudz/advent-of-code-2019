package aoc.day8;

import org.junit.Test;

import java.io.IOException;
import static aoc.utils.InputReader.getInput;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day8Tests {

    @Test
    public void testExample1() {
        String pixels = "123456789012";
        int imageSize = 6; //3*2
        Day8 puzzle = new Day8(pixels, imageSize);
        assertThat(puzzle.numberOfLayers(), equalTo(2));
    }

    @Test
    public void solvePart1() throws IOException {
        String pixels = getInput("/day8.txt");
        int imageSize = 25 * 6;
        //There are 100 layers
        Day8 puzzle = new Day8(pixels, imageSize);
        assertThat(puzzle.numberOfLayers(), equalTo(100));
        assertThat(puzzle.imageChecksum(), equalTo(2048));
    }

    @Test
    public void testPart2Example1() {
        String pixels = "0222112222120000";
        int imageSize = 4;
        Day8 puzzle = new Day8(pixels, imageSize);
        assertThat(puzzle.numberOfLayers(), equalTo(4));
        assertThat(puzzle.finalImage(), equalTo("0110"));
    }

    @Test
    public void solvePart2() throws IOException {
        String pixels = getInput("/day8.txt");
        int imageSize = 25 * 6;
        //There are 100 layers
        Day8 puzzle = new Day8(pixels, imageSize);
        assertThat(puzzle.finalImage(), equalTo("100101111010001011001001010010100001000110010101001111011100010101001011000100101000000100111101010010010100000010010010101001001010000001001001010010"));
        //seperated onto 25x6 grid spells HFYAK
    }

}
