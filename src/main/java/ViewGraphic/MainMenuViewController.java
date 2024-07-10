package ViewGraphic;

import Controller.*;
        import Model.ApplicationData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.Math.min;

public class MainMenuViewController {
    public Label coinLabel;
    public Label HPLabel;
    public Label XPLabel;
    public Label levelLabel;
    public Label nicknameLabel;

    public void initialize() {
        ApplicationData.getMainMenu().setController(this);
        coinLabel.setText("coin : \n" + ApplicationData.getHost().getCoins());
        HPLabel.setText("HP : \n" + ApplicationData.getHost().getHP());
        XPLabel.setText("XP : \n" + ApplicationData.getHost().getXp());
        levelLabel.setText("Level : \n" + ApplicationData.getHost().getLevel());
        nicknameLabel.setText(ApplicationData.getHost().getNickname());
    }

    public void goToGameMenu(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        try {
            new GameMenu().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToHistoryMenu(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        try {
            new HistoryMenu().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToShopMenu(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        try {
            new ShopMenu().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToSettingMenu(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        try {
            new SettingMenu().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToProfileMenu(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        try {
            new ProfileMenu().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void logout(MouseEvent mouseEvent) {
        Stage stage = ApplicationData.getStage();
        try {
            new LoginMenu().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
