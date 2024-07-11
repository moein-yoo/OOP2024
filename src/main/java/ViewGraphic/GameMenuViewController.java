package ViewGraphic;

import Controller.GameController;
import Controller.GameController2;
import Controller.RegistryMenuController;
import Model.ApplicationData;
import Model.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameMenuViewController {
    @FXML
    private Label timerLabel;
    @FXML
    private ChoiceBox<String> guestBox;
    @FXML
    private ChoiceBox<String> hostBox;
    @FXML
    private Label warningLabel;
    @FXML
    private Label betAmountLabel;
    @FXML
    private TextField usernameText;
    @FXML
    private TextField betAmountText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private ChoiceBox<String> choiceBox;
    private int timeSeconds = 5;
    private Timeline timeline;


    public void initialize() {
        ApplicationData.getGameMenu().setController(this);
        choiceBox.getItems().addAll("Normal Mode", "Bet Mode");
        hostBox.getItems().addAll("Dalghak", "Divaneh", "Motreb", "Guguli");
        hostBox.setValue("Dalghak");
        guestBox.getItems().addAll("Dalghak", "Divaneh", "Motreb", "Guguli");
        guestBox.setValue("Dalghak");
        choiceBox.setValue("Mode Selection");
        betAmountLabel.setOpacity(0);
        betAmountText.setOpacity(0);
        betAmountText.setDisable(true);
        choiceBox.setOnAction(mouseEvent -> {
            if (choiceBox.getValue().contains("Normal")) {
                betAmountLabel.setOpacity(0);
                betAmountText.setOpacity(0);
                betAmountText.setDisable(true);
            }
            else {
                betAmountLabel.setOpacity(1);
                betAmountText.setOpacity(1);
                betAmountText.setDisable(false);
            }
        });
    }

    public void startGame(MouseEvent mouseEvent) {
        String[] entered = new String[2];
        entered[0] = usernameText.getText();
        entered[1] = passwordText.getText();
        String outcome = RegistryMenuController.loginGraphicForGuest(entered);
        if (outcome.contains("successfully")) {
            ApplicationData.getHost().setCharacter(hostBox.getValue());
            ApplicationData.getGuest().setCharacter(guestBox.getValue());
            Game game;
            if (choiceBox.getValue().contains("Bet")) {
                ApplicationData.setBetStatus(true);
                ApplicationData.setBetCost(Integer.parseInt(betAmountText.getText()));
                game = new Game(Integer.parseInt(betAmountText.getText()));
            }
            else game = new Game();
            ApplicationData.setGame(game);
            GameController.setGame(game);
            GameController2.setGame(game);
            ApplicationData.setPlayingMode(true);
            Stage stage = ApplicationData.getStage();
            ApplicationData.getHost().buffCardOnCharacter();
            ApplicationData.getGuest().buffCardOnCharacter();
            try {
                new ViewGraphic.Game().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            warningLabel.setText(outcome);
            startTimer();
        }
    }

    public void goBack(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        stage.setScene(MainMenu.getScene());
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    private void startTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), e -> {
                    timeSeconds--;
                    timerLabel.setText(timeSeconds + " seconds left");
                    if (timeSeconds == 0) {
                        timeline.stop();
                    }
                })
        );
        timeline.play();
    }
}
