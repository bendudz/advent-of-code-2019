package aoc.day3;

import aoc.classes.Coordinate;
import aoc.classes.exceptions.InvalidCoordinateInstructionFound;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Day3 {
    // Say starting point is 0,0
    //R8 would mean 8,0
    //U5 then is 8,5
    //L5 is 3,5
    //D3 is 3,2
    // R L affect x axis
    // D U affect y axis
    // Find all co-ords between 2 points
    // Add all to 1 set (x,y,Set(points between))
    // Do the same for the other graph
    public static Coordinate STARTING_COORDINATE = new Coordinate(0,0);

    public static Integer manhattanDistanceToNearestIntersection(String path1, String path2) {
        LinkedList<Coordinate> routeA = generateCoordinateList(path1);
        LinkedList<Coordinate> routeB = generateCoordinateList(path2);

//        Intersections
        LinkedList<Coordinate> common = new LinkedList<>(routeA);
        common.retainAll(routeB);
        common.remove(0);

//        Workout manhattan
        Map<Integer, Coordinate> distancesFromCentre = new HashMap<>();
        for (Coordinate coordinate : common) {
            int distance = coordinate.manhattanDistance(new Coordinate(0,0));
            distancesFromCentre.put(distance, coordinate);
        }

        List<Integer> manhattanDistanceToIntersection = distancesFromCentre.keySet().stream().sorted().collect(Collectors.toList());
        return manhattanDistanceToIntersection.get(0);
    }

    public static LinkedList<Coordinate> generateCoordinateList(String instructions) {
        LinkedList<Coordinate> myPathCoordinates = new LinkedList<>();
        myPathCoordinates.add(STARTING_COORDINATE);

        for (final String instruction : instructions.split(",")) {
            String[] part = instruction.split("(?<=\\D)(?=\\d)");
            String direction = part[0];
            int distance = Integer.parseInt(part[1]);

            Coordinate previousCoord = myPathCoordinates.getLast();
            System.out.println(previousCoord);

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
        return myPathCoordinates;
    }
}
