package ViewGraphic;

import Controller.RegistryMenuController;
import Model.ApplicationData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class SignUpMenuViewController {
    @FXML
    private Label randomPassword;
    @FXML
    private TextField nickNameLabel;
    @FXML
    private TextField userNameLabel;
    @FXML
    private TextField passwordLabel;
    @FXML
    private TextField mailLabel;
    @FXML
    private TextField passwordRecoveryQuestion;
    @FXML
    private TextField passwordRecoveryType;
    @FXML
    private Button signUpButton;
    @FXML
    private Label warningLabel;

    @FXML
    public void initialize() {
        ApplicationData.getSignUpMenu().setController(this);
        signUpButton.setOnMouseClicked(mouseEvent -> {
            String[] enteredUser = new String[6];
            enteredUser[0] = userNameLabel.getText();
            enteredUser[1] = nickNameLabel.getText();
            enteredUser[2] = passwordLabel.getText();
            enteredUser[3] = mailLabel.getText();
            enteredUser[4] = passwordRecoveryQuestion.getText();
            enteredUser[5] = passwordRecoveryType.getText();
            String outcome = RegistryMenuController.signUp(enteredUser);
            if (outcome.contains("successfully")) {
                Stage stage = ApplicationData.getStage();
                try {
                    new LoginMenu().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                warningLabel.setText(outcome);
            }

        });
    }

    public void randomPasswordGenerator(MouseEvent mouseEvent) {
        String tempPassword = RegistryMenuController.randomPasswordMaker();
        randomPassword.setText(tempPassword);
    }

    public void goBack(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        stage.setScene(LoginMenu.getScene());
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
