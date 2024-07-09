package ViewGraphic;

import Controller.ProfileMenuController;
import Controller.RegistryMenuController;
import Model.ApplicationData;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginMenuViewController {
    @FXML
    private RadioButton radioButton;
    @FXML
    private Label passwordTitle;
    @FXML
    private Label questionTitle;
    @FXML
    private TextField recoveryQuestionAnswer;
    @FXML
    private PasswordField passwordLabel;
    @FXML
    private TextField usernameLabel;
    @FXML
    private Label messageLabel;
    private boolean passForgetting = false;
    @FXML
    public void initialize() {
        ApplicationData.getLoginMenu().setController(this);
    }

    public void login(MouseEvent mouseEvent) {
        if (!passForgetting) {
            String[] entered = {usernameLabel.getText(), passwordLabel.getText()};
            String outcome = RegistryMenuController.login(entered);
            if (outcome.contains("successfully")) {
                for (User user : ApplicationData.getUserArrayList()) {
                    if (user.getUsername().equals(usernameLabel.getText())) {
                        ApplicationData.setHost(user);
                    }
                }
            }
            else {
                messageLabel.setText(outcome);
            }
        }
        else {
            if (!RegistryMenuController.isUserValid(usernameLabel.getText())) {
                messageLabel.setText("Username does not exist");
            }
            else {
                for (User user : ApplicationData.getUserArrayList()) {
                    if (user.getUsername().equals(usernameLabel.getText())) {
                        if (user.getPasswordRecoveryQuestion().equals(recoveryQuestionAnswer.getText())) {
                            String outcome = ProfileMenuController.changePassword(passwordLabel.getText(), user);
                            if (outcome.contains("successfully")) {
                                ApplicationData.setHost(user);
                            }
                            else messageLabel.setText(outcome);
                        }
                    }
                }
            }
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

    public void passWordForget(MouseEvent mouseEvent) {
        if (!passForgetting) {
            passForgetting = true;
            recoveryQuestionAnswer.setOpacity(1);
            recoveryQuestionAnswer.setDisable(true);
            boolean vojood = false;
            for (User user : ApplicationData.getUserArrayList()) {
                if (user.getUsername().equals(usernameLabel.getText())) {
                    vojood = true;
                    if (user.getPasswordRecoveryType() == 1)
                        questionTitle.setText("Where is your hometown?");
                    else if (user.getPasswordRecoveryType() == 2)
                        questionTitle.setText("Who is your first school teacher?");
                    else if (user.getPasswordRecoveryType() == 3)
                        questionTitle.setText("Who is your love?!");
                    passwordTitle.setText("Your New password");
                    passwordLabel.setPromptText("New Password");
                }
            }
            if (!vojood) {
                messageLabel.setText("First You should enter your username then select this button");
                passForgetting = false;
                radioButton.setDisable(false);
                recoveryQuestionAnswer.setOpacity(0);
                recoveryQuestionAnswer.setDisable(false);
            }
        }
        else {
            passForgetting = false;
            questionTitle.setText("");
            passwordTitle.setText("Password");
            passwordLabel.setPromptText("Password");
            recoveryQuestionAnswer.setOpacity(0);
            recoveryQuestionAnswer.setDisable(false);
        }
    }
}
