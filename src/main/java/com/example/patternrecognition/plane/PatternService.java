package com.example.patternrecognition.plane;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * A service class for the PatternController class.
 * Class for using dependency injection.
 * @author NicolòRicciardo
 */
@Service
public class PatternService {

    private final List<Point> pointList = new ArrayList<>();
    private final List<Line> lineList = new ArrayList<>();
    private final List<Line> linesWithNPoints = new ArrayList<>();

    /**
     * Get all the points posted.
     * @return pointList The list of all points posted.
     */
    public List<Point> getPointList() {
        return pointList;
    }

    /**
     * Get all the lines created from the posted points.
     * @return lineList The list of all lines created.
     */
    public List<Line> getLineList() {
        return lineList;
    }

    /**
     * Add the point if the list of point is empty or if there are no
     * occurrences of the same point in the list.
     * Creates new lines everytime a point is successfully added.
     * The lines created start from the latest point and end at
     * all other points in the list.
     * Each time a point is added, [number of points -1] lines are created.
     * @param point to add in the plane.
     * @return a string which notifies the user with the result of the creation points operation.
     */
    public String createPoint(@RequestBody Point point) {
        Line line;
        String message = "Point successfully added to the plane!";
        // If there are no points yet, add the first without controls
        if(pointList.isEmpty()) {
            pointList.add(point);
        // Check that there are no identical points
        } else if(!pointList.contains(point)) {
            pointList.add(point);
            // Starting from the point just added, create new lines arriving to other points
            for(int i=0; i < pointList.size() -1; i++) {
                line = new Line(pointList.get(pointList.size()-1), pointList.get(i));
                lineList.add(line);
            }
            // Checks whether the newly added point belongs to other lines
            // if there's only 1 line it means that the point is at the start or at the end
            if(lineList.size() > 1) {
                for(Point p: pointList) {
                    for(Line l : lineList) {
                        // Add the point if is inside the segment
                        l.addPointToSegment(p);
                    }
                }
            }
        } else
            message = "ERROR: This point already exist in the plane. Try again.";
        return message;
    }

    /**
     * Check the
     * Checks that the occurrences of points in the line are
     * at least n. If true, add the line to the list with at least n points.
     * Clears the list every time is called.
     * @param n number of points inside the segments.
     * @return the list of lines with at least n points.
     */
    public List<Line> getLinesWithNPoints(Integer n) {
        if(!linesWithNPoints.isEmpty())
            linesWithNPoints.clear();
        for(Line line : lineList) {
            if(line.getLine().size() >= n)
                linesWithNPoints.add(line);
        }
        return linesWithNPoints;
    }

    /**
     * Delete all posted points. It also clears
     * the list with all the lines and the list of lines with
     * N points, if are not empty already.
     * @return a string which notifies the user with the result of the points removal operation.
     */
    public String deleteAllPoints() {
        if(!pointList.isEmpty())
            pointList.clear();
        if(!lineList.isEmpty())
            lineList.clear();
        if(!linesWithNPoints.isEmpty())
            linesWithNPoints.clear();
        return "All points removed from plane.";
    }
}
