package ViewGraphic;

import Controller.RegistryMenuController;
import Model.ApplicationData;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginMenu extends Application {
    private LoginMenuViewController controller;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //MainMenu.makeSinger();
        ApplicationData.setStage(stage);
        ApplicationData.getStage().getIcons().add(new Image(LoginMenu.class.getResource("/Media/Images/Logo.jpeg").toExternalForm()));
        ApplicationData.getStage().setTitle("M&S Card Game");
        ApplicationData.setLoginMenu(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(LoginMenu.class.getResource("/FXML/LoginMenu.fxml"));
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        pane.setBackground(new Background(createBackgroundImage()));
        stage.centerOnScreen();
        stage.show();
    }

    private static BackgroundImage createBackgroundImage() {
        Image image = new Image(LoginMenu.class.getResource("/Media/Images/Menus/LoginMenu.jpeg").toExternalForm(), 600, 400, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }

    public LoginMenuViewController getController() {
        return controller;
    }

    public void setController(LoginMenuViewController controller) {
        this.controller = controller;
    }
}
