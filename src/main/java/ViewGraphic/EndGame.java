package ViewGraphic;

import Controller.GameController2;
import Model.ApplicationData;
import View.EndGameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EndGame extends Application {
    private EndGameController controller;
    private static Scene scene;

    public static void main(String[] args){
        launch(args);
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        EndGame.scene = scene;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ApplicationData.setStage(stage);
        ApplicationData.getStage().getIcons().add(new Image(LoginMenu.class.getResource("/Media/Images/Logo.jpeg").toExternalForm()));
        ApplicationData.getStage().setTitle("M&S Card Game");
        ApplicationData.setEndGame(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(EndGame.class.getResource("/FXML/endGame.fxml"));
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        pane.setBackground(new Background(createBackgroundImage()));
        stage.centerOnScreen();
        stage.show();
    }

    private static BackgroundImage createBackgroundImage() {
        Image image = new Image(LoginMenu.class.getResource("/Media/Images/Menus/Game.jpg").toExternalForm(), 600 ,400, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }

    public EndGameController getController() {
        return controller;
    }

    public void setController(EndGameController controller) {
        this.controller = controller;
    }
}
