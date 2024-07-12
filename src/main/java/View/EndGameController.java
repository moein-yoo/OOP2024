package View;

import Model.ApplicationData;
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
    public void srt(ActionEvent actionEvent) {
        Stage stage= ApplicationData.getStage();
        try {
            new MainMenu().start(stage);
        }
        catch (Exception e){throw new RuntimeException(e);}
    }
}
