package com.example.patternrecognition;

import com.example.patternrecognition.plane.Line;
import com.example.patternrecognition.plane.Point;

import java.util.*;

public class Main {
    static ArrayList<Point> pointList = new ArrayList<>();
    static ArrayList<Line> lineList = new ArrayList<>();
    static ArrayList<Line> linesWithNPoints = new ArrayList<>();
    static Set<Point> setOfPoints = new LinkedHashSet<>();


    public static void main(String[] args) {

        // Program with created points
        /*Point a = new Point(-2, 8);
        Point b = new Point(8, 2);
        Point m = new Point(3, 5);
        Point c = new Point(5.5, 3.5);
        Point d = new Point(6, 8);
        Point e = new Point(-4, -3);
        createPoint(a);
        createPoint(b);
        createPoint(c);
        createPoint(d);
        createPoint(e);
        createPoint(m);*/

        Scanner scanner = new Scanner(System.in);

        // Program with points created by the user
        try {
            boolean addPoint = true;
            Point tmp;
            int response;
            double x, y;
            // Request points coordinates from user
            while (addPoint) {
                System.out.println("Insert the x:");
                x = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Insert the y:");
                y = scanner.nextDouble();
                scanner.nextLine();
                tmp = new Point(x, y);
                createPoint(tmp);
                System.out.println("Add more points? (YES=1 / NO=0)");
                response = scanner.nextInt();
                if (response != 1)
                    addPoint = false;
            }
        }catch (Exception ex) {
            System.out.println("ERROR: something went wrong.");
        }

        // This part is for both type of program. It prints the results
        if(!lineList.isEmpty()) {
            System.out.println("All the lines in the plane:");
            for(int i=0; i < lineList.size(); i++) {
                System.out.println(i+1 +") " + lineList.get(i));
            }
            try{
                System.out.println("\nHow many lines with N points or more?");
                System.out.println("Insert a number to check:");
                int num = scanner.nextInt();
                getLinesWithNPoints(num);
                if(!linesWithNPoints.isEmpty()) {
                    System.out.println("Lines with " + num + " or more points: " + linesWithNPoints.size());
                    System.out.println(linesWithNPoints);
                    System.out.println("Points involved:");
                    System.out.println(setOfPoints);
                } else
                    System.out.println("There aren't lines with " + num + " or more points.");
            } catch (Exception ex) {
                System.out.println("ERROR: something went wrong.");
            }
        }

    }

    static void createPoint(Point point) {
        Line line;
        // If there are no points yet, add the first without controls
        if (pointList.isEmpty()) {
            pointList.add(point);
            // Check that there are no identical points
        } else if (!pointList.contains(point)) {
            pointList.add(point);
            // Starting from the point just added, create new lines arriving to other points
            for (int i = 0; i < pointList.size() - 1; i++) {
                line = new Line(pointList.get(pointList.size() - 1), pointList.get(i));
                lineList.add(line);
            }
            // Checks whether the newly added point belongs to other lines
            // if there's only 1 line it means that the point is at the start or at the end
            if (lineList.size() > 1) {
                for (Point p : pointList) {
                    for (Line l : lineList) {
                        // Add the point if is inside the segment
                        l.addPointToSegment(p);
                    }
                }
            }
        }
    }

    static void getLinesWithNPoints(int n) {
        for (Line line : lineList) {
            if (line.getLine().size() >= n) {
                setOfPoints.addAll(line.getLine());
                linesWithNPoints.add(line);
            }
        }
    }

}
