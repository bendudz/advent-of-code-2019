package aoc.classes.exceptions;

public class InvalidCoordinateInstructionFound extends RuntimeException {
    public InvalidCoordinateInstructionFound(String s) {
        System.err.println(s);
    }
}
