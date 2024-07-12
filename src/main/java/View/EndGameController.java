package View;

import Model.ApplicationData;
import ViewGraphic.EndGame;
import ViewGraphic.MainMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EndGameController {
    @FXML
    public Button button;
    @FXML
    Label winnerLabel;
    @FXML
    Label loserLabel;

    public void initialize() {
        winnerLabel.setText("Award : " + EndGame.winner);
        loserLabel.setText("Punishment : " + EndGame.loser);
    }
    public void srt(ActionEvent actionEvent) {
        Stage stage= ApplicationData.getStage();
        try {
            new MainMenu().start(stage);
        }
        catch (Exception e){throw new RuntimeException(e);}
    }
}
