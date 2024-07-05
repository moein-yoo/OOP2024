package Controller;

import Model.MatchData;
import Model.User;

public class Main {
    public static void main(String[] args) {
        User.initialize();
        MatchData.initialize();
    }
}