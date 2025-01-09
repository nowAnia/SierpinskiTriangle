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

    public Triangle() {
        x = new Point2D(750.0, 50.0);
        y = new Point2D(50.0, 50.0);
        z = new Point2D(400.0, 550.0);
    }

    public Point2D getX() {
        return x;
    }

    public Point2D getY() {
        return y;
    }

    public Point2D getZ() {
        return z;
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
        TrianglePoints insideTriangle = createMidPoints(triangle);

        List<TrianglePoints> leftTriangles = new LinkedList<>();

        TrianglePoints triangle1 = new TrianglePoints(triangle.A(), insideTriangle.A(), insideTriangle.C());
        TrianglePoints triangle2 = new TrianglePoints(triangle.C(), insideTriangle.B(), insideTriangle.C());
        TrianglePoints triangle3 = new TrianglePoints(triangle.B(), insideTriangle.A(), insideTriangle.B());

        leftTriangles.add(triangle1);
        leftTriangles.add(triangle2);
        leftTriangles.add(triangle3);

        return leftTriangles;
    }

    public Polygon createPolygon(TrianglePoints excludeOnePolygon) {
        Polygon polygon = new Polygon();

        TrianglePoints insideTriangle = createMidPoints(excludeOnePolygon);
        polygon.getPoints().addAll(insideTriangle.A().getX(), insideTriangle.A().getY());
        polygon.getPoints().addAll(insideTriangle.B().getX(), insideTriangle.B().getY());
        polygon.getPoints().addAll(insideTriangle.C().getX(), insideTriangle.C().getY());

        currentColor = red;
        polygon.setFill(currentColor);

        return polygon;
    }

    private TrianglePoints createMidPoints(TrianglePoints outsideTriangle) {
        Point2D newX = outsideTriangle.A().midpoint(outsideTriangle.B());
        Point2D newY = outsideTriangle.B().midpoint(outsideTriangle.C());
        Point2D newZ = outsideTriangle.C().midpoint(outsideTriangle.A());

        return new TrianglePoints(newX, newY, newZ);
    }
}
