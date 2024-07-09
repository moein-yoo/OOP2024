package ViewGraphic;

import Controller.*;
import Model.ApplicationData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.Math.min;

public class ProfileMenuViewController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField nameLabel;
    @FXML
    private TextField lastNameLabel;
    @FXML
    private TextField userNameLabel;
    @FXML
    private TextField mailLabel;
    @FXML
    private TextField passwordLabel;
    @FXML
    private DatePicker birthCal;
    @FXML
    private TextField sexLabel;
    @FXML
    private Label nameLabel1;
    @FXML
    private Label lastNameLabel1;
    @FXML
    private Label userNameLabel1;
    @FXML
    private Label mailLabel1;
    @FXML
    private Label passwordLabel1;
    @FXML
    private Label birthCal1;
    @FXML
    private Label sexLabel1;
    @FXML
    private Button saveAndQuit;
    @FXML
    private Label warningLabel;

    public void initialize() {
        ApplicationData.getProfileMenu().setController(this);
        nameLabel1.setText(ApplicationData.getHost().getUsername() + " Changing to ->");
        lastNameLabel1.setText(ApplicationData.getHost().getNickname() + " Changing to ->");
        mailLabel1.setText(ApplicationData.getHost().get() + " Changing to ->");
        userNameLabel1.setText(ApplicationData.getHost().getUsername() + " Changing to ->");
        passwordLabel1.setText(ApplicationData.getHost().getPassword() + " Changing to ->");
        birthCal1.setText(ApplicationData.getHost().getCalendar() + " Changing to ->");
        sexLabel1.setText(ApplicationData.getHost().getSex() + " Changing to ->");
        saveAndQuit.setOnMouseClicked(mouseEvent -> {
            String[] enteredUser = new String[7];
            enteredUser[0] = nameLabel.getText()!=null? nameLabel.getText() : ApplicationData.getUser().getName();
            enteredUser[1] = lastNameLabel.getText()!=null? lastNameLabel.getText() : ApplicationData.getUser().getLastName();
            enteredUser[2] = userNameLabel.getText()!=null? userNameLabel.getText() : ApplicationData.getUser().getUserName();
            enteredUser[3] = mailLabel.getText()!=null? mailLabel.getText() : ApplicationData.getUser().getMail();
            enteredUser[4] = passwordLabel.getText()!=null? passwordLabel.getText() : ApplicationData.getUser().getPassword();
            enteredUser[5] = sexLabel.getText()!=null? sexLabel.getText() : ApplicationData.getUser().getSex();
            enteredUser[6] = birthCal.toString()!=null? birthCal.toString() : ApplicationData.getUser().getCalendar();
            if (!LoginMenuController.isNicknameCorrect(enteredUser[0]))
                warningLabel.setText("name format incorrect");
            else if (!LoginMenuController.isNicknameCorrect(enteredUser[1]))
                warningLabel.setText("LastName format incorrect");
            else if (!LoginMenuController.isUsernameCorrect(enteredUser[2]))
                warningLabel.setText("username's format incorrect");
            else if (LoginMenuController.isUserValid(enteredUser[2]))
                warningLabel.setText("username exist");
            else if (!LoginMenuController.isMailValid(enteredUser[3]))
                warningLabel.setText("mail's format incorrect");
//            else if (!LoginMenuController.isPasswordCorrect(enteredUser[5]))
//                warningLabel.setText("password is correct");
            else {
                Stage stage = ApplicationData.getStage();
                try {
                    new MainMenu().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
