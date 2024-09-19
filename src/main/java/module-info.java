module org.example.sierpinski {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.sierpinski to javafx.fxml;
    exports org.example.sierpinski;
}