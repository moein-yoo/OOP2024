package ViewGraphic;

import Controller.HistoryMenuController;
import Model.ApplicationData;
import Model.MatchData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

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
    public TableColumn awardColumn;
    public TableColumn penaltyColumn;
    public TableColumn oppColumn;
    public TableColumn oppLevColumn;
    public TableColumn hostLevColumn;
    public TableColumn resultColumn;
    public TableColumn timeColumn;
    @FXML
    private TableView<String> historyTable;
    static int currentPage=1;

    public void initialize() {
        ApplicationData.getHistoryMenu().setController(this);
        ArrayList<MatchData> allMatches= HistoryMenuController.getAllMatches();
        HistoryMenuController.sortOnDate(allMatches,false);
        HistoryMenuController.showAll(allMatches,1);

        nextPageButton.setOnMouseClicked(mouseEvent -> {
            int totalPages=allMatches.size()-(allMatches.size()%10);
            totalPages/=10;
            if(allMatches.size()%10!=0)
                totalPages+=1;
            if(currentPage==totalPages)
                System.out.println("You are already at last page");
            else{
                currentPage+=1;
                HistoryMenuController.showAll(allMatches,currentPage);
                
            }
        });

    }

    public void goBack(MouseEvent mouseEvent) {
    }
}
