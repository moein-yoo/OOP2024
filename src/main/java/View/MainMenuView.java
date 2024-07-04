package View;

import Controller.MainMenuController;
import Controller.ProfileMenuController;
import Model.ApplicationData;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenuView extends Menu {
    public static boolean run() {
        Pattern[] patterns = new Pattern[9];
        Scanner scanner = ApplicationData.getScanner();
        String input;
        patterns[0] = Pattern.compile("exit");
        patterns[1] = Pattern.compile("show current menu");
        patterns[2] = Pattern.compile("start game");
        patterns[3] = Pattern.compile("show cards");
        patterns[4] = Pattern.compile("go to shopmenu");
        patterns[5] = Pattern.compile("go to history");
        patterns[6] = Pattern.compile("go to profilemenu");
        patterns[7] = Pattern.compile("logout");
        patterns[8] = Pattern.compile("back");
        input = scanner.next();
        while (!input.equals("exit")) {
            boolean ejra = false;
            input += scanner.nextLine();
            if (input.matches(String.valueOf(patterns[0]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[0]));
                matcher.find();
                return false;
            } else if (input.matches(String.valueOf(patterns[1]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[1]));
                matcher.find();
                ejra = true;
                System.out.println("Profile Menu");
            } else if (input.matches(String.valueOf(patterns[2]))) {
                if (!MainMenuController.goToGameMenu())
                    return false;
                ejra = true;
            } else if (input.matches(String.valueOf(patterns[3]))) {
                showCards();
                ejra = true;
            } else if (input.matches(String.valueOf(patterns[4]))) {
                if (!MainMenuController.goToShopMenu())
                    return false;
                ejra = true;
            } else if (input.matches(String.valueOf(patterns[5]))) {
                if (!MainMenuController.goToHistory())
                    return false;
                ejra = true;
            } else if (input.matches(String.valueOf(patterns[6]))) {
                if (!MainMenuController.goToProfile())
                    return false;
                ejra = true;
            } else if (input.matches(String.valueOf(patterns[7]))) {
                MainMenuController.logout();
                return true;
            } else if (input.matches(String.valueOf(patterns[8]))) {
                return true;
            }
            if (!ejra) {
                System.out.println("invalid command");
            }
            input = scanner.next();
        }
        return false;
    }

    public static void showCards() {

    }
}
