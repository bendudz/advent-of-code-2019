package aoc.day10;

import aoc.classes.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LineOfSight {
    private final Coordinate targetAsteroid;
    private final double angle;
}
