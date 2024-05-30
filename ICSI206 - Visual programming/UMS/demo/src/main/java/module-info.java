module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.demo to javafx.fxml;
    opens Models to javafx.base;

    exports com.example.demo;
}