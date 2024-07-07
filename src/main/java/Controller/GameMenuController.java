package Controller;

import Model.ApplicationData;
import Model.User;
import View.RegistryMenuView;

import java.util.Scanner;

public class GameMenuController {
    public static String selectCharacter(String character, String username) {
        if (!character.equals("Motreb") || !character.equals("Dalghak") || !character.equals("Divaneh") || !character.equals("Guguli")) {
            return "Character type is incorrect";
        }
        else {
            for (User user : ApplicationData.getUserArrayList()) {
                if (user.getUsername().equals(username)) {
                    user.setCharacter(character);
                    return "Character " + character + " assigned to " + user.getNickname() + " successfully!";
                }
            }
        }
        return "";
    }
    public static void guestLogin() {
        String tempUsername = "", tempPassword = "";
        User tempUser = new User();
        String captcha = "";
        do {
            System.out.println("username for guestPlayer: ");
            Scanner scanner = ApplicationData.getScanner();
            tempUsername = scanner.nextLine();
            if (!RegistryMenuController.isUserValid(tempUsername))
                System.out.println("Username is not valid try again!");
            else break;
        } while (true);
        for (User user : ApplicationData.getUserArrayList()) {
            if (user.getUsername().equals(tempUsername)) {
                tempUser = user;
                break;            }
        }
        do {
            System.out.println("password for " + tempUsername + " : ");
            Scanner scanner = ApplicationData.getScanner();
            tempPassword = scanner.nextLine();
            if (!tempUser.getPassword().equals(tempPassword)) {
                System.out.println("Password is not valid try again after 5 seconds!");
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(5000); // Delay for 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        System.out.println(5-i + " seconds left for your next try");
                    }
                }
            }
            else {
                captcha = RegistryMenuView.captchaAsciiArtChecker();
                while (!captcha.contains("Not")) {
                    captcha = RegistryMenuView.captchaAsciiArtChecker();
                    System.out.println(captcha);
                }
                break;
            }
        } while (true);
        ApplicationData.setSecondLogin(true);
        ApplicationData.setGuest(tempUser);
    }
    public static String betChoose(int value) {
        ApplicationData.setBetStatus(true);
        ApplicationData.setBetCost(value);
        return "Bet mode on!";
    }
}
