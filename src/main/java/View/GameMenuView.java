package View;

import Controller.GameMenuController;
import Controller.RegistryMenuController;
import Model.ApplicationData;
import Model.Game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenuView extends Menu {
    public static boolean run() {
        ApplicationData.setSecondLogin(false);
        GameMenuController.guestLogin();
        Pattern[] patterns = new Pattern[6];
        Scanner scanner = ApplicationData.getScanner();
        String input;
        patterns[0] = Pattern.compile("exit");
        patterns[1] = Pattern.compile("show current menu");
        patterns[2] = Pattern.compile("start game");
        patterns[3] = Pattern.compile("select bet mode");
        patterns[4] = Pattern.compile("select character for u (?<username>[\\S]+) c (?<character>[\\S]+)");
        patterns[5] = Pattern.compile("back");
        input = scanner.nextLine();
        while (!input.equals("exit")) {
            boolean ejra = false;
            if (input.matches(String.valueOf(patterns[0]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[0]));
                matcher.find();
                return false;
            } else if (input.matches(String.valueOf(patterns[1]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[1]));
                matcher.find();
                ejra = true;
                System.out.println("Game selecting Menu");
            } else if (input.matches(String.valueOf(patterns[2]))) {
                if (ApplicationData.isSecondLogin() && !ApplicationData.getHost().getCharacter().equals("nothing")
                        && !ApplicationData.getGuest().getCharacter().equals("nothing")) {
                    Game game = new Game();
                    if (ApplicationData.isBetStatus())
                        game = new Game(ApplicationData.getBetCost());
                    ApplicationData.setGame(game);
                    return GameView.run(game);
                }
                ejra = true;
            } else if (input.matches(String.valueOf(patterns[3]))) {
                System.out.println("How much you bet : ");
                input = scanner.nextLine();
                System.out.println(GameMenuController.betChoose(Integer.parseInt(input)));
                ejra = true;
            } else if (input.matches(String.valueOf(patterns[4]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[4]));
                matcher.find();
                String tempUsername = matcher.group("username");
                String tempCharacter = matcher.group("character");
                if (!RegistryMenuController.isUserValid(tempUsername)
                        && !ApplicationData.getHost().getUsername().equals(tempUsername)
                        && !ApplicationData.getGuest().getUsername().equals(tempUsername)) {
                    System.out.println("The entered username is invalid!");
                }
                System.out.println(GameMenuController.selectCharacter(tempCharacter, tempUsername));
                ejra = true;
            } else if (input.matches(String.valueOf(patterns[5]))) {
                return true;
            }
            if (!ejra) {
                System.out.println("invalid command");
            }
            input = scanner.nextLine();
        }
        return false;
    }
}