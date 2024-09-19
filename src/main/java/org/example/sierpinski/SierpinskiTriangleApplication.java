package org.example.sierpinski;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SierpinskiTriangleApplication extends Application {
    public static final String APP_VIEW_FILE_NAME = "sierpinski-triangle-view.fxml";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SierpinskiTriangleApplication.class.getResource(APP_VIEW_FILE_NAME));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        stage.setTitle("Sierpiński Triangle!");
        stage.setScene(scene);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}