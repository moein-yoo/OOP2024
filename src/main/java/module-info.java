module OOP2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.java;
    exports ViewGraphic;
    opens ViewGraphic to javafx.fxml;
}