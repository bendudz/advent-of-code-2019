package aoc.classes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

import static java.lang.Math.abs;

@Data
@AllArgsConstructor
public class Coordinate {
    int x;
    int y;

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
        for (int i = this.x + 1; i <= this.x + distance; i++) {
            list.add(new Coordinate(i, this.y));
        }
        return list;
    }

    public LinkedList<Coordinate> turnLeftAndMoveAndRegisterPoints(int distance) {
        LinkedList<Coordinate> list = new LinkedList<>();
        for (int i = this.x - 1; i >= this.x - distance; i--) {
            list.add(new Coordinate(i, this.y));
        }
        return list;
    }

    public LinkedList<Coordinate> turnUpAndMoveAndRegisterPoints(int distance) {
        LinkedList<Coordinate> list = new LinkedList<>();
        for (int i = this.y + 1; i <= this.y + distance; i++) {
            list.add(new Coordinate(this.x, i));
        }
        return list;
    }

    public LinkedList<Coordinate> turnDownAndMoveAndRegisterPoints(int distance) {
        LinkedList<Coordinate> list = new LinkedList<>();
        for (int i = this.y - 1; i >= this.y - distance; i--) {
            list.add(new Coordinate(this.x, i));
        }
        return list;
    }

}
