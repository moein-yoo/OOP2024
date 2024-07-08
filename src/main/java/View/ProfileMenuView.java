package View;

import Controller.ProfileMenuController;
import Model.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuView extends Menu {
    public static boolean run() {
        Pattern[] patterns = new Pattern[9];
        Scanner scanner = ApplicationData.getScanner();
        String input;
        patterns[0] = Pattern.compile("exit");
        patterns[1] = Pattern.compile("show current menu");
        patterns[2] = Pattern.compile("change password (?<oldpassword>[\\S]+) (?<newpassword>[\\S]+)");
        patterns[6] = Pattern.compile("change username (?<newUsername>[\\S]+)");
        patterns[7] = Pattern.compile("change nickname (?<newNickname>[\\S]+)");
        patterns[8] = Pattern.compile("change email (?<newEmail>[\\S]+)");
        patterns[3] = Pattern.compile("show information");
        patterns[4] = Pattern.compile("remove account (?<currentPassword>[\\S]+)");
        patterns[5] = Pattern.compile("back");
        input = scanner.next();
        while (!input.equals("exit")){
            boolean ejra = false;
            input += scanner.nextLine();
            if(input.matches(String.valueOf(patterns[0]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[0]));
                matcher.find();
                return false;
            }
            else if (input.matches(String.valueOf(patterns[1]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[1]));
                matcher.find();
                ejra = true;
                System.out.println("Profile Menu");
            }
            else if (input.matches(String.valueOf(patterns[2]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[2]));
                matcher.find();
                System.out.println(ProfileMenuController.changePassword(matcher, ApplicationData.getHost()));
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[6]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[6]));
                matcher.find();
                String enter = matcher.group("newUsername");
                System.out.println(ProfileMenuController.changeUsername(enter));
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[7]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[7]));
                matcher.find();
                String enter = matcher.group("newNickname");
                System.out.println(ProfileMenuController.changeNickname(enter));
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[8]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[8]));
                matcher.find();
                String enter = matcher.group("newEmail");
                System.out.println(ProfileMenuController.changeEmail(enter));
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[3]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[3]));
                matcher.find();
                System.out.printf("username : %s\n",ApplicationData.getHost().getUsername());
                System.out.printf("nickname : %s\n",ApplicationData.getHost().getNickname());
                System.out.printf("email : %s\n",ApplicationData.getHost().getEmail());
                System.out.printf("level : %d\n",ApplicationData.getHost().getLevel());
                System.out.printf("coin : %d\n",ApplicationData.getHost().getCoins());
                System.out.printf("xp : %d\n",ApplicationData.getHost().getXp());
                System.out.printf("hp : %d\n",ApplicationData.getHost().getHP());

                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[4]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[4]));
                matcher.find();
                ejra = true;
                if(ProfileMenuController.deleteAccount(matcher, ApplicationData.getHost())) {
                    System.out.println("Account deleted successfully");
                    return true;
                }
            }
            else if (input.matches(String.valueOf(patterns[5]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[5]));
                matcher.find();
                return true;
            }
            if(!ejra) {
                System.out.println("invalid command");
            }
            input = scanner.next();
        }
        return false;
    }
}
