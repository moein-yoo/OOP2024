package Controller;

import Model.Card;
import Model.MatchData;
import Model.User;
import View.RegistryMenuView;

public class Main {
    public static void main(String[] args) {
        Card.initialize();
        User.initialize();
        MatchData.initialize();
        RegistryMenuView.run();
    }
}