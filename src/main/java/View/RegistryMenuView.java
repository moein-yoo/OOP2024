package View;

import Controller.RegistryMenuController;
import Model.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistryMenuView extends Menu{
    public void run() {
        Scanner scanner = ApplicationData.getScanner();
        String input;
        Pattern[] patterns = new Pattern[10];
        patterns[0] = Pattern.compile("exit");
        patterns[1] = Pattern.compile("show current menu");
        //(String username,String nickname,String password,String email,String passwordRecoveryQuestion,int character)
        patterns[2] = Pattern.compile("register u (?<username>[\\S]+) n (?<nickname>[\\S\\s]+) p (?<password>[\\S]+) em (?<email>[\\S]+) t (?<type>[\\d])");
        patterns[5] = Pattern.compile("ans (?<answer>[\\S\\s]+)");
        patterns[3] = Pattern.compile("login u (?<username>[\\S]+) p (?<password>[\\S]+)");
        patterns[4] = Pattern.compile("list of users");
        input = scanner.nextLine();
        while (!input.equals("exit")){
            boolean ejra = false;
            if(input.matches(String.valueOf(patterns[0]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[0]));
                matcher.find();
                return;
            }
            else if (input.matches(String.valueOf(patterns[1]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[0]));
                matcher.find();
                ejra = true;
                System.out.println("Register Menu");
            }
            else if (input.matches(String.valueOf(patterns[2]))) {
                Matcher matcher = getCommandMatcher(input,String.valueOf(patterns[2]));
                matcher.find();
                ejra = true;
                String[] entered = new String[6];
                entered[0] = matcher.group("username");
                entered[1] = matcher.group("nickname");
                entered[2] = matcher.group("password");
                entered[3] = matcher.group("email");
                entered[4] = matcher.group("type");
                if (Integer.parseInt(entered[4]) == 1) {
                    System.out.println("Where is your hometown?");
                } else if (Integer.parseInt(entered[5]) == 2) {
                    System.out.println("Who is your first school teacher?");
                }
                else System.out.println("Who is your love?!");
                input = scanner.nextLine();
                if (input.matches(String.valueOf(patterns[5]))) {
                    matcher = getCommandMatcher(input,String.valueOf(patterns[5]));
                    matcher.find();
                    entered[5] = matcher.group("answer");
                    RegistryMenuController.signin(entered);
                }else {
                    ejra = false;
                }
            }
            else if (input.matches(String.valueOf(patterns[3]))) {
                Matcher matcher = getCommandMatcher(input,String.valueOf(patterns[3]));
                matcher.find();
                String[] entered = new String[2];
                entered[0] = matcher.group("username");
                entered[1] = matcher.group("password");
                String outcome = RegistryMenuController.login(entered);
                System.out.println(outcome);
                if (outcome.contains("successfully")) {
                    if (!MainMenuView.run())
                        return;
                }
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[4]))) {
                Matcher matcher = getCommandMatcher(input,String.valueOf(patterns[4]));
                matcher.find();
                ArrayList<String> outcome = RegistryMenuController.userList();
                for (String s : outcome) {
                    System.out.print(s);
                }
                ejra = true;
            }
            if(!ejra) {
                System.out.println("invalid command");
            }
            input = scanner.nextLine();
        }

    }
}
