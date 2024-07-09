package Controller;

import Model.ApplicationData;
import Model.Card;
import Model.MatchData;
import Model.User;
import View.RegistryMenuView;

import java.sql.SQLException;

public class Main extends ApplicationData {
    public static void main(String[] args) throws SQLException {
        Card.initialize();
        User.initialize();
        MatchData.initialize();
//        RegistryMenuView.run();

    }
}