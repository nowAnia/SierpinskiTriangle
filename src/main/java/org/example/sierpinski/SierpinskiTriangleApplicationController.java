package org.example.sierpinski;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;


public class SierpinskiTriangleApplicationController {
    @FXML
    private Group group;

    private final Triangle triangle = new Triangle();

    private final TrianglePoints points = new TrianglePoints(triangle.getX(), triangle.getY(), triangle.getZ());

    private List<TrianglePoints> activeTriangles = new ArrayList<>();

    @FXML
    public void initialize() {
        activeTriangles.add(points);

        startGame();
    }

    @FXML
    protected void onGoDeeperClick() {
        group.getChildren().add(triangle.keepCreateTriangle());
    }

    @FXML
    protected void onGoCreateFractal() {
        List<TrianglePoints> newActiveTriangles = new ArrayList<>();
        for (var trianglePoints : activeTriangles) {
            newActiveTriangles.addAll(recursiveWay(trianglePoints));
        }

        activeTriangles = newActiveTriangles;
    }

    private void startGame() {
        group.getChildren().add(triangle.drawTriangle());
    }

    private List<TrianglePoints> recursiveWay(TrianglePoints points) {
        Polygon excludedTriangle = triangle.createPolygon(points);
        group.getChildren().add(excludedTriangle);

        return triangle.createFractal(points);
    }
}