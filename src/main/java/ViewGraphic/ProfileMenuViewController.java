package ViewGraphic;

import Controller.*;
import Model.ApplicationData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.Math.min;

public class ProfileMenuViewController {
    public Label usernameLabel;
    public Label nicknameLabel;
    public Label passwordLabel;
    public Label mailLabel;
    @FXML
    private Button backButton;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private TextField textField;
    @FXML
    private Button save;
    @FXML
    private Label warningLabel;

    public void initialize() {
        ApplicationData.getProfileMenu().setController(this);
        usernameLabel.setText("Username : " + ApplicationData.getHost().getUsername());
        nicknameLabel.setText("NickName : " + ApplicationData.getHost().getNickname());
        passwordLabel.setText("Password : " + ApplicationData.getHost().getPassword());
        mailLabel.setText("Email : " + ApplicationData.getHost().getEmail());
        choiceBox.getItems().addAll("Username", "Nickname", "Password", "Email");
        choiceBox.setOnAction(mouseEvent -> {
            textField.setPromptText(choiceBox.getValue());
        });
        save.setOnMouseClicked(mouseEvent -> {
            if (choiceBox.getValue().equals("Username")) {
                warningLabel.setText(ProfileMenuController.changeUsername(textField.getText()));
            }
            else if (choiceBox.getValue().equals("Nickname")) {
                warningLabel.setText(ProfileMenuController.changeNickname(textField.getText()));
            }
            else if (choiceBox.getValue().equals("Password")) {
                warningLabel.setText(ProfileMenuController.changePassword(textField.getText(), ApplicationData.getHost()));
            }
            else if (choiceBox.getValue().equals("Email")) {
                warningLabel.setText(ProfileMenuController.changeEmail(textField.getText()));
            }
            usernameLabel.setText("Username : " + ApplicationData.getHost().getUsername());
            nicknameLabel.setText("NickName : " + ApplicationData.getHost().getNickname());
            passwordLabel.setText("Password : " + ApplicationData.getHost().getPassword());
            mailLabel.setText("Email : " + ApplicationData.getHost().getEmail());
        });
        backButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = ApplicationData.getStage();
            try {
                new MainMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
