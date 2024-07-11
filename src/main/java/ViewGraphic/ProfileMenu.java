package ViewGraphic;

import Model.ApplicationData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class ProfileMenu extends Application {
    private ProfileMenuViewController controller;
    public static void main(String[] args){launch(args);}

    @Override
    public void start(Stage stage) throws Exception {
        ApplicationData.setStage(stage);
        ApplicationData.setProfileMenu(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(LoginMenu.class.getResource("/FXML/ProfileMenu.fxml"));
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        pane.setBackground(new Background(createBackgroundImage()));
        stage.centerOnScreen();
        stage.show();
    }

    private BackgroundImage createBackgroundImage() {
        Image image = new Image(LoginMenu.class.getResource("/Media/Images/Menus/ProfileMenu.jpeg").toExternalForm(), 717, 644, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }

    public ProfileMenuViewController getController() {
        return controller;
    }

    public void setController(ProfileMenuViewController controller) {
        this.controller = controller;
    }
}
