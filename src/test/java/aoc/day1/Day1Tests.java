package aoc.day1;

import org.junit.Test;

import java.io.IOException;

import static aoc.day1.Day1.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Day1Tests {
    @Test
    public void testAOCDay1Examples() {
        assertThat(fuelForMass(12), equalTo(2));
        assertThat(fuelForMass(14), equalTo(2));
        assertThat(fuelForMass(1969), equalTo(654));
        assertThat(fuelForMass(100756), equalTo(33583));
    }

    @Test
    public void testRecursiveFuelRequirement() {
        assertThat(fuelForMassAndFuel(1969), equalTo(966));
    }

    @Test
    public void testDay1Calculations() throws IOException {
        String input = "/day1.txt";
        assertThat(puzzlePart1(input), equalTo(3246455));
        assertThat(puzzlePart2(input), equalTo(4866824));
    }
}
