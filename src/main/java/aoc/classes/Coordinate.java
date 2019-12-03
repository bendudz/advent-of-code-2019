package aoc.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedList;
import java.util.Optional;

import static java.lang.Math.abs;

@Data
@AllArgsConstructor
public class Coordinate {
    int x;
    int y;
    @EqualsAndHashCode.Exclude
    private int distance;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Optional<Integer> getDistance() {
        return Optional.of(this.distance);
    }

    public int manhattanDistance(int x, int y) {
        return abs(this.x - x) + abs(this.y - y);
    }

    public int manhattanDistance(Coordinate another) {
        return manhattanDistance(another.x, another.y);
    }

    public Coordinate turnRightAndMove(int distance) {
        return new Coordinate(this.x + distance, this.y);
    }

    public Coordinate turnLeftAndMove(int distance) {
        return new Coordinate(this.x - distance, this.y);
    }

    public Coordinate turnUpAndMove(int distance) {
        return new Coordinate(this.x, this.y + distance);
    }

    public Coordinate turnDownAndMove(int distance) {
        return new Coordinate(this.x, this.y - distance);
    }

    public LinkedList<Coordinate> turnRightAndMoveAndRegisterPoints(int distance) {
        LinkedList<Coordinate> list = new LinkedList<>();
        int currDist = 0;
        for (int i = this.x + 1; i <= this.x + distance; i++) {
            currDist = getCurrentDistance(list, currDist);
            list.add(new Coordinate(i, this.y, currDist + 1));
        }
        return list;
    }

    public LinkedList<Coordinate> turnLeftAndMoveAndRegisterPoints(int distance) {
        LinkedList<Coordinate> list = new LinkedList<>();
        int currDist = 0;
        for (int i = this.x - 1; i >= this.x - distance; i--) {
            currDist = getCurrentDistance(list, currDist);
            list.add(new Coordinate(i, this.y, currDist + 1));
        }
        return list;
    }

    public LinkedList<Coordinate> turnUpAndMoveAndRegisterPoints(int distance) {
        LinkedList<Coordinate> list = new LinkedList<>();
        int currDist = 0;
        for (int i = this.y + 1; i <= this.y + distance; i++) {
            currDist = getCurrentDistance(list, currDist);
            list.add(new Coordinate(this.x, i, currDist + 1));
        }
        return list;
    }

    public LinkedList<Coordinate> turnDownAndMoveAndRegisterPoints(int distance) {
        LinkedList<Coordinate> list = new LinkedList<>();
        int currDist = 0;
        for (int i = this.y - 1; i >= this.y - distance; i--) {
            currDist = getCurrentDistance((LinkedList<Coordinate>) list, currDist);
            list.add(new Coordinate(this.x, i, currDist + 1));
        }
        return list;
    }

    private int getCurrentDistance(LinkedList<Coordinate> list, int currDist) {
        if (this.distance == 0 && list.isEmpty()) {
            currDist = 0;
        } else if (this.distance == 0 && !list.isEmpty()) {
            currDist = list.getLast().getDistance().get();
        } else if (this.distance > 0 && list.isEmpty()) {
            currDist = this.getDistance().get();
        } else if (list.getLast().getDistance().isPresent()) {
            currDist = list.getLast().getDistance().get();
        }
        return currDist;
    }
}
