package aoc.day3;

import aoc.classes.Coordinate;
import aoc.classes.exceptions.InvalidCoordinateInstructionFound;

import java.util.*;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;


public class Day3 {

    public static Coordinate STARTING_COORDINATE = new Coordinate(0,0, 0);

    public static Integer manhattanDistanceToNearestIntersectionFromOrigin(String path1, String path2) {
        LinkedList<Coordinate> common = findIntersectionExcludingDistance(path1, path2);

        List<Integer> manhattanDistancesToIntersection = getManhattanDistanceFromOrigin(common).keySet().stream().sorted().collect(toList());
        return manhattanDistancesToIntersection.get(0);
    }

    public static Integer closestIntersectionBySteps(String path1, String path2) {
        LinkedList<Coordinate> common = findIntersections(path1, path2);
        return common.get(0).getDistance().get();
    }

    private static LinkedList<Coordinate> findIntersectionExcludingDistance(String path1, String path2) {
        LinkedList<Coordinate> routeA = generateCoordinateList(path1);
        LinkedList<Coordinate> routeB = generateCoordinateList(path2);

        LinkedList<Coordinate> intersections = new LinkedList<>(routeA);
        intersections.retainAll(routeB);
        intersections.remove(0);
        return intersections;
    }

    private static LinkedList<Coordinate> findIntersections(String path1, String path2) {
        LinkedList<Coordinate> routeA = generateCoordinateList(path1);
        LinkedList<Coordinate> routeB = generateCoordinateList(path2);

        LinkedList<Coordinate> intersections = new LinkedList<>(routeA);
        intersections.retainAll(routeB);
        intersections.remove(0); //removing (0,0)

        final List<Coordinate> combinedIntersections = new ArrayList<>();

        routeA.forEach(c ->
                routeB.stream()
                        .filter(c::equals)
                        .forEach(combinedIntersections::add));

        combinedIntersections.addAll(intersections);
        combinedIntersections.sort(Comparator.comparing(Coordinate::getX).thenComparing(Coordinate::getY));
        combinedIntersections.remove(0);

        LinkedList<Coordinate> newCoord = new LinkedList<>();
        Coordinate prevCoord = combinedIntersections.get(0);


        for (int i=1; i < combinedIntersections.size(); i++) {
            prevCoord = combinedIntersections.get(i);
            if (i % 2 != 0) {
                int newDist = prevCoord.getDistance().get() + combinedIntersections.get(i-1).getDistance().get();
                newCoord.add(new Coordinate(combinedIntersections.get(i).getX(),combinedIntersections.get(i).getY(),newDist));
            }
        }

        newCoord.sort(Comparator.comparing(c -> c.getDistance().get()));

        return newCoord;
    }


    private static Map<Integer, Coordinate> getManhattanDistanceFromOrigin(LinkedList<Coordinate> common) {
        Map<Integer, Coordinate> distancesFromCentre = new HashMap<>();
        for (Coordinate coordinate : common) {
            int distance = coordinate.manhattanDistance(STARTING_COORDINATE);
            distancesFromCentre.put(distance, coordinate);
        }
        return distancesFromCentre;
    }

    public static LinkedList<Coordinate> generateCoordinateList(String instructions) {
        LinkedList<Coordinate> myPathCoordinates = new LinkedList<>();
        myPathCoordinates.add(STARTING_COORDINATE);

        for (final String instruction : instructions.split(",")) {
            String[] part = instruction.split("(?<=\\D)(?=\\d)");
            String direction = part[0];
            int distance = Integer.parseInt(part[1]);

            Coordinate previousCoord = myPathCoordinates.getLast();

            switch (direction) {
                case "R":
                    myPathCoordinates.addAll(previousCoord.turnRightAndMoveAndRegisterPoints(distance));
                    break;
                case "L":
                    myPathCoordinates.addAll(previousCoord.turnLeftAndMoveAndRegisterPoints(distance));
                    break;
                case "U":
                    myPathCoordinates.addAll(previousCoord.turnUpAndMoveAndRegisterPoints(distance));
                    break;
                case "D":
                    myPathCoordinates.addAll(previousCoord.turnDownAndMoveAndRegisterPoints(distance));
                    break;
                default:
                    throw new InvalidCoordinateInstructionFound(format("Found: %s" , direction));
            }
        }
        System.out.println("MyCoordList: " + myPathCoordinates);
        return myPathCoordinates;
    }
}
