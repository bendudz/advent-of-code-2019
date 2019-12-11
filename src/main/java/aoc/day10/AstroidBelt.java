package aoc.day10;

import aoc.classes.Coordinate;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Data
public class AstroidBelt {
    private final Coordinate topLeft;
    private final Coordinate bottomRight;

    public AstroidBelt(Coordinate topLeft, Coordinate bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public LinkedList<Coordinate> generateEmptyCanvas() {
        //(0,0) - (4,4)
        int topLeftX = topLeft.getX();
        int topLeftY = topLeft.getY();
        int bottomRightX = bottomRight.getX();
        int bottomRightY = bottomRight.getY();
        LinkedList<Coordinate> canvas = new LinkedList<>();
        for (int x = topLeftX; x < bottomRightX; x++) {
            for (int y = topLeftY; y < bottomRightY; y++) {
                canvas.add(new Coordinate(x,y));
            }
        }
        return canvas;
    }

}
