package com.example.patternrecognition.plane;

import java.util.Objects;

/**
 * Representation of a point on a plane.
 * A point is formed by a pair of double, the x and y coordinates.
 * @author NicolòRicciardo
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructor of the Point class.
     * @param x value of the x-coordinate of the point.
     * @param y value of the y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x-coordinate.
     * @return x The x-coordinate of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * Set the x-coordinate.
     * @param x the value to set the x-coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Get the y-coordinate.
     * @return y the y-coordinate of the point.
     */
    public double getY() {
        return y;
    }

    /**
     * Set the y-coordinate.
     * @param y the value to set the y-coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Compares this point to the specified object. The result is true if and only if the
     * argument is not null and is a Point object with the same x and y coordinates.
     * @param o object to check if is equal.
     * @return true if the given object represents a Point equivalent to this point, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
