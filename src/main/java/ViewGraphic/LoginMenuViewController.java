package ViewGraphic;

import Controller.RegistryMenuController;
import Model.ApplicationData;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginMenuViewController {
    @FXML
    private PasswordField passwordLabel;
    @FXML
    private TextField usernameLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Label messageLabel;
    @FXML
    private Button signUpButton;
    @FXML
    private Button quitButton;
    @FXML
    public void initialize() {
        ApplicationData.getLoginMenu().setController(this);
    }

    public void login(MouseEvent mouseEvent) {
        String[] entered = {usernameLabel.getText(), passwordLabel.getText()};
        String outcome = RegistryMenuController.login(entered);
        if (outcome.contains("successfully")) {}
        else {
            messageLabel.setText(outcome);
        }
    }

    public void signUp(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        try {
            new SignUpMenu().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void quit(MouseEvent mouseEvent) {
        ApplicationData.getStage().close();
    }
}
