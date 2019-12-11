package aoc.day10;

import aoc.classes.Coordinate;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static aoc.day10.Position.*;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day10Tests {
    @Test
    public void testCanvasGenerator() {
        Coordinate topLeft = new Coordinate(0,0);
        Coordinate bottomRight = new Coordinate(5,5);
        AstroidBelt belt = new AstroidBelt(topLeft, bottomRight);
        assertThat(belt.generateEmptyCanvas().size(), equalTo(25));
    }

    @Test
    public void day10EaxmpleTest1() {
        String input = ".#..#\n.....\n#####\n....#\n...##";
        List<Coordinate> coords = Position.mapToCanvas(input);
        assertThat(coords.size(), equalTo(25));
    }

    @Test
    public void givenAstroidMap_thenDeserialiseToPositionalDTO() {
        String input = ".#..#\n.....\n#####\n....#\n...##";
        List<Position> asteroidPositions = Position.mapToPositions(input);
        List<Coordinate> asteroids = asteroidPositions.stream().filter(Position::isAsteroid).map(Position::getCoordinate).collect(toList());
        asteroids.forEach(i -> {
            System.out.println(format("(%d,%d)",i.getX(), i.getY()));
        });
    }

@Test
    public void givenAstroidMap_thenDeserialiseToPositional() {
        String input = ".#..#\n.....\n#####\n....#\n...##";
        List<Position> asteroidPositions = Position.mapToPositions(input);
        List<Position> b =  asteroidPositions.stream().map(a -> detectAsteroids(a, Position.mapToPositions(input))).collect(toList());
        Position pos = Collections.max(b, Comparator.comparing(Position::getLineOfSight));
        assertThat(pos.getCoordinate(), equalTo(new Coordinate(3,4)));
    }

    @Test
    public void testLargeExample1() {
        String input = "......#.#.\n#..#.#....\n..#######.\n.#.#.###..\n.#..#.....\n..#....#.#\n#..#....#.\n.##.#..###\n##...#..#.\n.#....####\n";
        List<Position> asteroidPositions = Position.mapToPositions(input);
        List<Position> uniquePositions =  asteroidPositions.stream().map(a -> detectAsteroids(a, Position.mapToPositions(input))).collect(toList());
        Position bestPosition = Collections.max(uniquePositions, Comparator.comparing(Position::getLineOfSight));
        assertThat(bestPosition, equalTo(new Position(5,8,true,33)));
    }


    @Test
    public void testLargeExample2() {
        String input = "#.#...#.#.\n.###....#.\n.#....#...\n##.#.#.#.#\n....#.#.#.\n.##..###.#\n..#...##..\n..##....##\n......#...\n.####.###.\n";
        List<Position> asteroidPositions = Position.mapToPositions(input);
        List<Position> uniquePositions =  asteroidPositions.stream().map(a -> detectAsteroids(a, Position.mapToPositions(input))).collect(toList());
        Position bestPosition = Collections.max(uniquePositions, Comparator.comparing(Position::getLineOfSight));
        assertThat(bestPosition, equalTo(new Position(1,2,true,35)));
    }

    @Test
    public void testLargeExample3() {
        String input = ".#..#..###\n####.###.#\n....###.#.\n..###.##.#\n##.##.#.#.\n....###..#\n..#.#..#.#\n#..#.#.###\n.##...##.#\n.....#.#..";
        List<Position> asteroidPositions = Position.mapToPositions(input);
        List<Position> uniquePositions =  asteroidPositions.stream().map(a -> detectAsteroids(a, Position.mapToPositions(input))).collect(toList());
        Position bestPosition = Collections.max(uniquePositions, Comparator.comparing(Position::getLineOfSight));
        assertThat(bestPosition, equalTo(new Position(6,3,true,41)));
    }

    @Test
    public void testLargeExample4() {
        String input = ".#..##.###...#######\n##.############..##.\n.#.######.########.#\n.###.#######.####.#.\n#####.##.#.##.###.##\n..#####..#.#########\n####################\n#.####....###.#.#.##\n##.#################\n#####.##.###..####..\n..######..##.#######\n####.##.####...##..#\n.#####..#.######.###\n##...#.##########...\n#.##########.#######\n.####.#.###.###.#.##\n....##.##.###..#####\n.#.#.###########.###\n#.#.#.#####.####.###\n###.##.####.##.#..##";
        List<Position> asteroidPositions = Position.mapToPositions(input);
        List<Position> uniquePositions =  asteroidPositions.stream().map(a -> detectAsteroids(a, Position.mapToPositions(input))).collect(toList());
        Position bestPosition = Collections.max(uniquePositions, Comparator.comparing(Position::getLineOfSight));
        assertThat(bestPosition, equalTo(new Position(11,13,true,210)));
    }

    @Test
    public void day10SolvePuzzlePart1() {
        String input = "#...##.####.#.......#.##..##.#.\n#.##.#..#..#...##..##.##.#.....\n#..#####.#......#..#....#.###.#\n...#.#.#...#..#.....#..#..#.#..\n.#.....##..#...#..#.#...##.....\n##.....#..........##..#......##\n.##..##.#.#....##..##.......#..\n#.##.##....###..#...##...##....\n##.#.#............##..#...##..#\n###..##.###.....#.##...####....\n...##..#...##...##..#.#..#...#.\n..#.#.##.#.#.#####.#....####.#.\n#......###.##....#...#...#...##\n.....#...#.#.#.#....#...#......\n#..#.#.#..#....#..#...#..#..##.\n#.....#..##.....#...###..#..#.#\n.....####.#..#...##..#..#..#..#\n..#.....#.#........#.#.##..####\n.#.....##..#.##.....#...###....\n###.###....#..#..#.....#####...\n#..##.##..##.#.#....#.#......#.\n.#....#.##..#.#.#.......##.....\n##.##...#...#....###.#....#....\n.....#.######.#.#..#..#.#.....#\n.#..#.##.#....#.##..#.#...##..#\n.##.###..#..#..#.###...#####.#.\n#...#...........#.....#.......#\n#....##.#.#..##...#..####...#..\n#.####......#####.....#.##..#..\n.#...#....#...##..##.#.#......#\n#..###.....##.#.......#.##...##";
        List<Position> asteroidPositions = Position.mapToPositions(input);
        List<Position> uniquePositions =  asteroidPositions.stream().map(a -> detectAsteroids(a, Position.mapToPositions(input))).collect(toList());
        Position bestPosition = Collections.max(uniquePositions, Comparator.comparing(Position::getLineOfSight));
        assertThat(bestPosition, equalTo(new Position(17,22,true,288)));
    }

    @Test
    public void solvePart2() {
        String input = "#...##.####.#.......#.##..##.#.\n#.##.#..#..#...##..##.##.#.....\n#..#####.#......#..#....#.###.#\n...#.#.#...#..#.....#..#..#.#..\n.#.....##..#...#..#.#...##.....\n##.....#..........##..#......##\n.##..##.#.#....##..##.......#..\n#.##.##....###..#...##...##....\n##.#.#............##..#...##..#\n###..##.###.....#.##...####....\n...##..#...##...##..#.#..#...#.\n..#.#.##.#.#.#####.#....####.#.\n#......###.##....#...#...#...##\n.....#...#.#.#.#....#...#......\n#..#.#.#..#....#..#...#..#..##.\n#.....#..##.....#...###..#..#.#\n.....####.#..#...##..#..#..#..#\n..#.....#.#........#.#.##..####\n.#.....##..#.##.....#...###....\n###.###....#..#..#.....#####...\n#..##.##..##.#.#....#.#......#.\n.#....#.##..#.#.#.......##.....\n##.##...#...#....###.#....#....\n.....#.######.#.#..#..#.#.....#\n.#..#.##.#....#.##..#.#...##..#\n.##.###..#..#..#.###...#####.#.\n#...#...........#.....#.......#\n#....##.#.#..##...#..####...#..\n#.####......#####.....#.##..#..\n.#...#....#...##..##.#.#......#\n#..###.....##.#.......#.##...##";
        List<Position> asteroidPositions = Position.mapToPositions(input);
        List<Position> uniquePositions =  asteroidPositions.stream().map(a -> detectAsteroids(a, Position.mapToPositions(input))).collect(toList());
        Position bestPosition = Collections.max(uniquePositions, Comparator.comparing(Position::getLineOfSight));
        assertThat(bestPosition, equalTo(new Position(17,22,true,288)));
        //blast astroids in order with limit...
        Coordinate finalAsteroid = blastAsteroids(bestPosition,Position.mapToPositions(input),200);
        assertThat(finalAsteroid, equalTo(new Coordinate(6,16)));
    }
}
