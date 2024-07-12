module OOP2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.java;
    exports ViewGraphic;
    opens ViewGraphic to javafx.fxml;
    exports Controller;
    opens Controller to javafx.fxml;
    exports ViewGraphic.Animation;
    opens ViewGraphic.Animation to javafx.fxml;
    exports Model;
    opens Model to javafx;
    exports View;
    opens View to javafx.fxml;
}
///