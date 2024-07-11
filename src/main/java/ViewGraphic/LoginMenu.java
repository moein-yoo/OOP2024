package ViewGraphic;

import Controller.RegistryMenuController;
import Model.ApplicationData;
import Model.Card;
import Model.MatchData;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class LoginMenu extends Application {
    private LoginMenuViewController controller;
    private static Scene scene;

    public static void main(String[] args){
        launch(args);
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        LoginMenu.scene = scene;
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Card.initialize();
//        User.initialize();
//        MatchData.initialize();
        makeImage();
        if (ApplicationData.getMediaPlayer()== null || ApplicationData.getMediaPlayer().isMute()) {
            ApplicationData.setMediaPlayer(new MediaPlayer(ApplicationData.getMedia()));
            ApplicationData.getMediaPlayer().play();
        }
        ApplicationData.setStage(stage);
        ApplicationData.getStage().getIcons().add(new Image(LoginMenu.class.getResource("/Media/Images/Logo.jpeg").toExternalForm()));
        ApplicationData.getStage().setTitle("M&S Card Game");
        ApplicationData.setLoginMenu(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(LoginMenu.class.getResource("/FXML/LoginMenu.fxml"));
        scene = new Scene(pane);
        stage.setScene(scene);
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

    public static void makeImage() {
        for (Card card : ApplicationData.getAllCardsArraylist()) {
            try {
                Image image = new Image(LoginMenu.class.getResource("/Media/Images/Cards/" + card.getName() + ".jpeg").toExternalForm(),100,100,false,false);
                card.setImage(image);
                ApplicationData.addImageToCardsImage(card.getName(), image);
            }catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }
}
