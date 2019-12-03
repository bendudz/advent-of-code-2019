package aoc.day3;

import aoc.classes.Coordinate;
import org.junit.Test;

import java.io.IOException;

import static aoc.day3.Day3.closestIntersectionBySteps;
import static aoc.day3.Day3.manhattanDistanceToNearestIntersectionFromOrigin;
import static aoc.utils.InputReader.getInput;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class Day3Tests {


    @Test
    public void day3Examples() {
        String path1 = "R8,U5,L5,D3";
        String path2 = "U7,R6,D4,L4";
        assertThat(manhattanDistanceToNearestIntersectionFromOrigin(path1,path2), equalTo(6));
    }

    @Test
    public void day3Examples2() {
        String path1 = "R75,D30,R83,U83,L12,D49,R71,U7,L72";
        String path2 = "U62,R66,U55,R34,D71,R55,D58,R83";
        assertThat(manhattanDistanceToNearestIntersectionFromOrigin(path1,path2), equalTo(159));
    }

    @Test
    public void day3Examples3() {
        String path1 = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51";
        String path2 = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";
        assertThat(manhattanDistanceToNearestIntersectionFromOrigin(path1,path2), equalTo(135));
    }

    @Test
    public void day3Puzzle() throws IOException {
        String[] input = getInput("/day3.txt").split("\n");
        assertThat(manhattanDistanceToNearestIntersectionFromOrigin(input[0],input[1]), equalTo(248));
    }

    @Test
    public void day3Part2Examples() {
        String path1 = "R75,D30,R83,U83,L12,D49,R71,U7,L72";
        String path2 = "U62,R66,U55,R34,D71,R55,D58,R83";
        assertThat(closestIntersectionBySteps(path1,path2), equalTo(610));
    }

    @Test
    public void day3Part2MadeUpExamples() {
        String path1 = "R8,U5,L5,D3";
        String path2 = "U7,R6,D4,L4";
        assertThat(closestIntersectionBySteps(path1,path2), equalTo(30));
    }

    @Test
    public void day3Part2MadeUpExamples2() {
        String path1 = "R2,U2,L1";
        String path2 = "L1,U2,R2";
        assertThat(closestIntersectionBySteps(path1,path2), equalTo(10));
    }

    @Test
    public void day3Part2Puzzle() throws IOException {
        String[] input = getInput("/day3.txt").split("\n");
        assertThat(closestIntersectionBySteps(input[0],input[1]), equalTo(28580));
    }

    @Test
    public void testCoordinateEquality() {
        Coordinate a = new Coordinate(32,43, 22);
        Coordinate b = new Coordinate(32,43, 23);
        Coordinate c = new Coordinate(33,42, 24);

        assertEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(b, c);
    }
}
