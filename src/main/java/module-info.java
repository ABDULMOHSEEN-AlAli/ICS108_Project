module com.example.project_ics108 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.project_ics108 to javafx.fxml;
    exports com.example.project_ics108;
}