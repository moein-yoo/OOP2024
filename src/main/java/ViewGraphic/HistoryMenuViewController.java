package ViewGraphic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class HistoryMenuViewController {
    @FXML
    public Button awardSort;
    @FXML
    public Button PenaltySort;
    @FXML
    public Button opponentSort;
    @FXML
    public Button hostLevelSort;
    @FXML
    public Button oppLevelSort;
    @FXML
    public Button resultSort;
    @FXML
    public Button timeSort;
    @FXML
    public Button nextPageButton;
    @FXML
    public Button previousPageButton;
    @FXML
    private TableView<String> historyTable;

    public void goBack(MouseEvent mouseEvent) {
    }
}
