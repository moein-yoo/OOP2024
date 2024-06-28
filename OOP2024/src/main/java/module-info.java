module Pacman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    exports View;
    opens View to javafx.fxml;
}