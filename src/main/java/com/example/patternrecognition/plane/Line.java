package com.example.patternrecognition.plane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representation of a line on a plane.
 * A line is formed by a set of points.
 * A line is provided also with a vector,
 * obtained from the difference between
 * the starting point and the end point of the line
 * @author NicolòRicciardo
 */
public class Line {

    private final List<Point> line = new ArrayList<>();
    private final Point vector;

    /**
     * Constructor of the Line class.
     * Add the points in the lines and calculate the vector.
     * @param a starting point of the line.
     * @param b end point of the line.
     */
    public Line(Point a, Point b) {
        this.line.add(a);
        this.line.add(b);
        vector = new Point(b.getX() - a.getX(), b.getY() - a.getY());
    }

    /**
     * Get the line without the vector.
     * @return line the list of points into the line.
     */
    public List<Point> getLine() {
        return line;
    }

    /**
     * Point p will be added to the line if it lies between the start and end of the line
     * and if it is not already present in the segment.
     * A line can be vertical, horizontal or oblique.
     * For this reason there are 3 different controls.
     * @param p point to add to line.
     */
    public void addPointToSegment(Point p) {
        if(!line.contains(p)) {
            // Checks that the line is vertical and that the point to be added
            // is between the beginning and the end of the line.
            if(vertical() && betweenV(p))
                line.add(p);
            // Checks that the line is horizontal and that the point to be added
            // is between the beginning and the end of the line.
            else if (horizontal() && betweenOr(p))
                line.add(p);
            // Checks that the point to be added is between the beginning
            // and the end of the line. It also checks that the point belongs to the line.
            else if(betweenOb(p) && belongsTo(p))
                line.add(p);
        }
    }

    /**
     * A point belongs to an oblique line if and only if
     * [alpha*vector = pointToAdd - startOfTheLine]
     * @param a the point to check.
     * @return true if the point belongs to the line passing between the two points.
     */
    private boolean belongsTo(Point a) {
        double firstValue, secondValue;

        firstValue = (a.getX() - line.get(0).getX()) / vector.getX();
        secondValue = (a.getY() - line.get(0).getY()) / vector.getY();

        return Math.abs(firstValue) == Math.abs(secondValue);
    }

    /**
     * Check to see if point p belongs to the vertical segment.
     * The point belongs to the segment if and only if it has the same
     * x-coordinate and the y-coordinate is between the starting and ending point.
     * @param p point to check.
     * @return true if the point belongs to the segment.
     */
    private boolean betweenV(Point p) {
        // Check if same x-coordinate
        if(p.getX() == line.get(0).getX()) {
            // Line bottom to top
            if(p.getY() > line.get(0).getY() && p.getY() < line.get(1).getY())
                return true;
            // Line top to bottom
            return p.getY() < line.get(0).getY() && p.getY() > line.get(1).getY();
        }
        return false;
    }

    /**
     * Check to see if point p belongs to the horizontal segment.
     * The point belongs to the segment if and only if it has the same
     * y-coordinate and the x-coordinate is between the starting and ending point.
     * @param p point to check.
     * @return true if the point belongs to the segment.
     */
    private boolean betweenOr(Point p) {
        // Check if same y-coordinate
        if(p.getY() == line.get(0).getY()) {
            // Line from left to right
            if(p.getX() > line.get(0).getX() && p.getX() < line.get(1).getX())
                return true;
            // Line from right to left
            return p.getX() < line.get(0).getX() && p.getX() > line.get(1).getX();
        }
        return false;
    }

    /**
     * Check to see if point p belongs to the oblique segment.
     * The point belongs to the segment if and only if its coordinates
     * are between the starting and ending point.
     * @param p point to check.
     * @return true fi the point belongs to the oblique segment, false otherwise.
     */
    private boolean betweenOb(Point p) {
        // Point to check is located to the right of the starting point
        if(p.getX() > line.get(0).getX() && p.getX() < line.get(1).getX()) {
            // Line from bottom-left to top-right
            if(p.getY() > line.get(0).getY() && p.getY() < line.get(1).getY())
                return true;
            else
                // Line from top-left to bottom-right
                return p.getY() < line.get(0).getY() && p.getY() > line.get(1).getY();
        // Point to check is located to the left of the starting point
        }else if(p.getX() < line.get(0).getX() && p.getX() > line.get(1).getX()) {
            // Line from top-right to bottom-left
            if(p.getY() < line.get(0).getY() && p.getY() > line.get(1).getY())
                return true;
            else
                // Line from bottom-right to top-left
                return p.getY() > line.get(0).getY() && p.getY() < line.get(1).getY();
        }
        return false;
    }

    /**
     * Check if the line is vertical.
     * @return true if is vertical.
     */
    private boolean vertical() {
        return vector.getX() == 0;
    }

    /**
     * Check if the line is horizontal.
     * @return true if is horizontal.
     */
    private boolean horizontal() {
        return vector.getY() == 0;
    }

    @Override
    public String toString() {
        return "Line{" +
                line +
                '}';
    }

    /**
     * Compares this line to the specified object. The result is true if and only if the
     * argument is not null, is a Line object and has the same vector.
     * The value of the two vectors can also be inverse because the direction of
     * the line doesn't matter as the segments have no direction.
     * @param o object to check if is equal.
     * @return true if the given object represents a Line equivalent to this line, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line otherLine = (Line) o;
        if(Objects.equals(vector, otherLine.vector)) return true;
        return (vector.getX() == -otherLine.vector.getX())
                && (vector.getY() == -otherLine.vector.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(line);
    }
}
