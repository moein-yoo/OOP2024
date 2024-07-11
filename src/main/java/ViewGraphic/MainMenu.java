package ViewGraphic;

import Model.ApplicationData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMenu extends Application {
    private MainMenuViewController controller;
    private static Scene scene;

    public static void main(String[] args){
        launch(args);
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        MainMenu.scene = scene;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //MainMenu.makeSinger();
        ApplicationData.setPlayingMode(false);
        ApplicationData.setStage(stage);
        ApplicationData.getStage().getIcons().add(new Image(LoginMenu.class.getResource("/Media/Images/Logo.jpeg").toExternalForm()));
        ApplicationData.getStage().setTitle("M&S Card Game");
        ApplicationData.setMainMenu(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(LoginMenu.class.getResource("/FXML/MainMenu.fxml"));
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        pane.setBackground(new Background(createBackgroundImage()));
        stage.centerOnScreen();
        stage.show();
    }

    private static BackgroundImage createBackgroundImage() {
        Image image = new Image(LoginMenu.class.getResource("/Media/Images/Menus/MainMenu.jpeg").toExternalForm(), 768, 503, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }

    public MainMenuViewController getController() {
        return controller;
    }

    public void setController(MainMenuViewController controller) {
        this.controller = controller;
    }
}
