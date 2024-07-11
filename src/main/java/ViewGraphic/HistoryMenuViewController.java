package ViewGraphic;

import Controller.HistoryMenuController;
import Model.ApplicationData;
import Model.MatchData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HistoryMenuViewController {
    @FXML
    private Button nextPageButton;
    @FXML
    private Button previousPageButton;
    @FXML
    private TableView<MatchData> historyTable;
    static int currentPage=1;

    public void initialize() {
        ApplicationData.getHistoryMenu().setController(this);
        ArrayList<MatchData> allMatches= HistoryMenuController.getAllMatches();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                int numberOfColumns = historyTable.getColumns().size();

                for (TableColumn<MatchData, ?> column : historyTable.getColumns()) {
                    //
                }

                historyTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Award"));
                historyTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Penalty"));
                historyTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Winner"));
                historyTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Loser"));
                historyTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("WinnerLevel"));
                historyTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("LoserLevel"));
                historyTable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Date"));

                ObservableList<MatchData> data = FXCollections.observableArrayList();
                for (MatchData matchData : allMatches) {
                    data.add(matchData);
                }
                historyTable.setItems(data);
            }
        });
    }

    public void goBack(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        stage.setScene(MainMenu.getScene());
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
