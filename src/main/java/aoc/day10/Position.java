package aoc.day10;

import aoc.classes.Coordinate;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class Position {
    private final Coordinate coordinate;
    private final boolean asteroid;
    private final Integer lineOfSight;

    public Position(int x, int y, boolean asteroid, int LOS) {
        this.coordinate = new Coordinate(x,y);
        this.asteroid = asteroid;
        this.lineOfSight = LOS;
    }

    public Position(int x, int y, boolean asteroid) {
        this.coordinate = new Coordinate(x,y);
        this.asteroid = asteroid;
        this.lineOfSight = 0;
    }

    public Position(int x, int y, int distance, boolean asteroid) {
        this.coordinate = new Coordinate(x,y,distance);
        this.asteroid = asteroid;
        this.lineOfSight = 0;
    }

    public Position(Coordinate coordinate, boolean asteroid, int LOS) {
        this.coordinate = coordinate;
        this.asteroid = asteroid;
        this.lineOfSight = LOS;
    }

    public static LinkedList<Coordinate> mapToCanvas(String input) {
        List<String> items = Arrays.asList(input.split("\n"));
        Coordinate topLeft = new Coordinate(0,0);
        Integer elementCount = getNumberOfElements(items);
        Coordinate bottomRight = new Coordinate(elementCount, items.size());
        return new AstroidBelt(topLeft,bottomRight).generateEmptyCanvas();
    }

    private static Integer getNumberOfElements(List<String> items) {
        return items.stream().map(String::length).findAny().get();
    }

    public static List<Position> mapToPositions(String input) {
        String[] items = input.split("\n");
        List<Position> positions = new ArrayList<>();
        int whichEntryInList = 0;
        for (String item : items) {
            for (int i = 0; i < item.length(); i++) {
                char g = item.charAt(i);
                switch (g) {
                    case '.':
                        break;
                    case '#':
                        positions.add(new Position(i, whichEntryInList,0, true));
                        break;
                    default:
                        throw new IllegalArgumentException("Excpected . or # but found " + g);
                }

            }
            whichEntryInList++;
        }
        return positions.stream()
                .sorted(Comparator.comparing(
                        (Position p) -> p.getCoordinate().getX())
                        .thenComparing(p -> p.getCoordinate().getY()))
                .collect(Collectors.toList());

    }

    public static Position detectAsteroids(Position source, List<Position> asteroidMap) {
        // Order positions based on source
        asteroidMap.remove(source);
        Map<Double, Coordinate> angles = new HashMap<>();
        for (Position asteroid : asteroidMap) {
            double angle = headingToAsteroid(source.getCoordinate(), asteroid.getCoordinate());
            if(!angles.containsKey(angle)) {
                angles.put(angle, asteroid.getCoordinate());
            }
        }
        return new Position(source.getCoordinate(), true, angles.size());
    }

    public static Coordinate blastAsteroids(Position bestPosition, List<Position> asteroidMap, Integer blastQty) {
        // Build full position, remove it from the map
        Position init = new Position(bestPosition.getCoordinate(),true,0);
        asteroidMap.remove(init);
        List<LineOfSight> lineOfSights = new ArrayList<>();
        for (Position asteroid : asteroidMap) {
            Coordinate c = new Coordinate();
            c.setX(asteroid.getCoordinate().getX());
            c.setY(asteroid.getCoordinate().getY());
            c.setDistance(c.manhattanDistance(init.getCoordinate()));
            lineOfSights.add(new LineOfSight(c, headingToAsteroid(init.getCoordinate(), asteroid.getCoordinate())));
        }
        lineOfSights.sort(Comparator.comparing(LineOfSight::getAngle)
                .thenComparing(i -> i.getTargetAsteroid().getDistance().get()));
        List<LineOfSight> byeBye = blastThem(lineOfSights, blastQty);
        return byeBye.get(blastQty-1).getTargetAsteroid();
    }

    private static List<LineOfSight> blastThem(List<LineOfSight> blasted, Integer blastHowMany) {
        return blasted.stream().filter(distinctByKey(LineOfSight::getAngle)).limit(blastHowMany).collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static double headingToAsteroid(Coordinate source, Coordinate target) {
        // inverse tan ((y2 -y1) / (x2 -x1))
        double x1 = source.getX();
        double y1 = source.getY();

        //When moving the origin the formula in this grid style is (x2-x1), (y1-y2)
        Coordinate newTarget = new Coordinate((int) (target.getX() - x1), (int) y1 - target.getY());
        double x2 = newTarget.getX();
        double y2 = newTarget.getY();

        //work out which half target is in for heading
        double retVal;

        if (x2 >=0) {
            //right
            retVal = 90 -  Math.toDegrees(Math.atan(y2 / x2));
        } else {
            retVal = 270 - Math.toDegrees(Math.atan(y2 / x2));
        }

        return retVal;
    }
}
