package aoc.day1;

import java.io.*;

import java.util.Objects;
import java.util.function.Function;

import static aoc.utils.InputReader.getInputAsStream;

public class Day1 {

    public static int fuelForMass(int mass) {
        return mass / 3 - 2;
    }

    public static int fuelForMassAndFuel(int mass) {
        int fuel = fuelForMass(mass);
        return fuel <= 0 ? 0 : fuel + fuelForMassAndFuel(fuel);
    }

    public static String fileToSolution(String fileName, Function<Integer, Integer> fuelFunction) throws IOException {
        return Objects.requireNonNull(getInputAsStream(fileName))
                .map(fuelFunction)
                .reduce(Integer::sum)
                .map(String::valueOf)
                .orElseThrow();
    }

    public static int puzzlePart1(String fileName) throws IOException {
        return Integer.parseInt(fileToSolution(fileName, Day1::fuelForMass));
    }

    public static int puzzlePart2(String fileName) throws IOException {
        return Integer.parseInt(fileToSolution(fileName, Day1::fuelForMassAndFuel));
    }

}
