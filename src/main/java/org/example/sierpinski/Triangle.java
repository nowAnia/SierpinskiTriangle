package org.example.sierpinski;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.LinkedList;
import java.util.List;

public class Triangle {
    private Point2D x;
    private Point2D y;
    private Point2D z;
    private final Color red = javafx.scene.paint.Color.RED;
    private final Color black = javafx.scene.paint.Color.BLACK;
    private Color currentColor;

    public Point2D getX() {
        return x;
    }

    public Point2D getY() {
        return y;
    }

    public Point2D getZ() {
        return z;
    }

    public Triangle() {
        x = new Point2D(750.0, 50.0);
        y = new Point2D(50.0, 50.0);
        z = new Point2D(400.0, 550.0);
    }

    public Polygon drawTriangle() {
        Polygon polygon = new Polygon();

        polygon.getPoints().addAll(x.getX(), x.getY(), y.getX(), y.getY(), z.getX(), z.getY());

        polygon.setFill(javafx.scene.paint.Color.BLACK);
        currentColor = black;

        return polygon;
    }

    public Polygon keepCreateTriangle() {
        Polygon polygon = new Polygon();

        Point2D newX = x.midpoint(y);
        Point2D newY = y.midpoint(z);
        Point2D newZ = z.midpoint(x);

        polygon.getPoints().addAll(newX.getX(), newX.getY(), newY.getX(), newY.getY(), newZ.getX(), newZ.getY());

        x = newX;
        y = newY;
        z = newZ;

        if (currentColor.equals(red)) {
            currentColor = black;
        } else {
            currentColor = red;
        }

        polygon.setFill(currentColor);

        return polygon;
    }

    public List<TrianglePoints> createFractal(TrianglePoints triangle) {
        List<Point2D> listOfPoints = new LinkedList<>();
        listOfPoints.add(triangle.x());
        listOfPoints.add(triangle.y());
        listOfPoints.add(triangle.z());

        List<Point2D> listOfMidPoints = createMidPoints(listOfPoints);

        List<TrianglePoints> leftTriangles = new LinkedList<>();

        TrianglePoints triangle1 = new TrianglePoints(listOfPoints.get(0), listOfMidPoints.get(0), listOfMidPoints.get(2));
        TrianglePoints triangle2 = new TrianglePoints(listOfPoints.get(2), listOfMidPoints.get(1), listOfMidPoints.get(2));
        TrianglePoints triangle3 = new TrianglePoints(listOfPoints.get(1), listOfMidPoints.get(0), listOfMidPoints.get(1));

        leftTriangles.add(triangle1);
        leftTriangles.add(triangle2);
        leftTriangles.add(triangle3);

        return leftTriangles;
    }

    public Polygon createPolygon(TrianglePoints excludeOnePolygon) {
        List<Point2D> listOfPoints = new LinkedList<>();

        listOfPoints.add(excludeOnePolygon.x());
        listOfPoints.add(excludeOnePolygon.y());
        listOfPoints.add(excludeOnePolygon.z());

        Polygon polygon = new Polygon();

        List<Point2D> listOfMidPoints = createMidPoints(listOfPoints);

        for (var point : listOfMidPoints) {
            polygon.getPoints().addAll(point.getX(), point.getY());
        }

        currentColor = red;
        polygon.setFill(currentColor);

        return polygon;
    }

    private List<Point2D> createMidPoints(List<Point2D> listPoints) {
        List<Point2D> midPoints = new LinkedList<Point2D>();

        Point2D x = listPoints.get(0);
        Point2D y = listPoints.get(1);
        Point2D z = listPoints.get(2);

        Point2D newX = x.midpoint(y);
        midPoints.add(newX);
        Point2D newY = y.midpoint(z);
        midPoints.add(newY);

        Point2D newZ = z.midpoint(x);
        midPoints.add(newZ);

        return midPoints;
    }
}
