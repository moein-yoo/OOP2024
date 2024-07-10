package ViewGraphic;

import Controller.RegistryMenuController;
import Model.ApplicationData;
import View.RegistryMenuView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class SignUpMenuViewController {
    @FXML
    private Label captcha;
    @FXML
    private TextField captchaAnswer;
    @FXML
    private ChoiceBox<String> recoveryQuestionBox;
    @FXML
    private TextField recoveryQuestionAnswer;
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
    private Button signUpButton;
    @FXML
    private Label warningLabel;
    private int type;
    private String[] captchaString;

    @FXML
    public void initialize() {
        recoveryQuestionBox.getItems().addAll("Where is your hometown?", "Who is your first school teacher?", "Who is your love?!");
        captchaString = RegistryMenuView.captchaAsciiArtCheckerGraphic();
        captcha.setText(captchaString[0]);
        recoveryQuestionBox.setValue("Where is your hometown?");
        ApplicationData.getSignUpMenu().setController(this);
        signUpButton.setOnMouseClicked(mouseEvent -> {
            String[] enteredUser = new String[6];
            enteredUser[0] = userNameLabel.getText();
            enteredUser[1] = nickNameLabel.getText();
            enteredUser[2] = passwordLabel.getText();
            enteredUser[3] = mailLabel.getText();
            enteredUser[5] = recoveryQuestionAnswer.getText();
            if (recoveryQuestionBox.getValue().contains("hometown")) type = 1;
            else if (recoveryQuestionBox.getValue().contains("school")) type = 2;
            else if (recoveryQuestionBox.getValue().contains("love")) type = 3;
            enteredUser[4] = String.valueOf(type);
            String outcome = RegistryMenuController.signUp(enteredUser);
            if (!captchaAnswer.getText().equalsIgnoreCase(captchaString[1])) {
                captchaString = RegistryMenuView.captchaAsciiArtCheckerGraphic();
                captcha.setText(captchaString[0]);
            }
            else if (outcome.contains("successfully")) {
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
