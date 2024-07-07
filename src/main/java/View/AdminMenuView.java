package View;

import Controller.AdminMenuController;
import Controller.ProfileMenuController;
import Model.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminMenuView extends Menu {
    public static boolean run() {
        Pattern[] patterns = new Pattern[9];
        Scanner scanner = ApplicationData.getScanner();
        String input;
        patterns[0] = Pattern.compile("exit");
        patterns[1] = Pattern.compile("show current menu");
        patterns[2] = Pattern.compile("add card n (?<name>\\S+) char (?<character>\\S+) ac (?<accuracy>\\d+) dur (?<duration>\\d+) dam (?<damage>\\d+) lev (?<level>\\d+) cost (?<upgradeCost>\\d+)");
        patterns[3] = Pattern.compile("show card list");
        patterns[4] = Pattern.compile("show dettail of card (?<cardNumber>[\\d]+)");
        patterns[5] = Pattern.compile("edit card (?<cardNumber>\\d+) n (?<name>\\S*)\\s*char (?<character>\\S*)\\s*ac (?<accuracy>\\d*)\\s*dur (?<duration>\\d*)\\s*dam (?<damage>\\d*)\\s*lev (?<level>\\d*)\\s*cost (?<upgradeCost>\\d*)");
        patterns[6] = Pattern.compile("remove card (?<cardNumber>\\d+)");
        patterns[7] = Pattern.compile("back");
        patterns[8] = Pattern.compile("back to login");
        input = scanner.nextLine();
        while (!input.equals("exit")){
            boolean ejra = false;
            if(input.matches(String.valueOf(patterns[0]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[0]));
                matcher.find();
                return false;
            }
            else if (input.matches(String.valueOf(patterns[1]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[1]));
                matcher.find();
                ejra = true;
                System.out.println("Admin Menu");
            }
            else if (input.matches(String.valueOf(patterns[2]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[2]));
                matcher.find();
                String[] entered = new String[7];
                entered[0] = matcher.group("name");
                entered[1] = matcher.group("character");
                entered[2] = matcher.group("accurracy");
                entered[3] = matcher.group("duration");
                entered[4] = matcher.group("damage");
                entered[5] = matcher.group("level");
                entered[6] = matcher.group("upgradeCost");
                System.out.println(AdminMenuController.addNewCard(entered));
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[3]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[3]));
                matcher.find();
                for (int i = 0; i < ApplicationData.getAllCardsArraylist().size(); i++) {
                    System.out.println(i+1 + " : " + ApplicationData.getAllCardsArraylist().get(i).getName());
                }
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[4]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[4]));
                matcher.find();
                int index = Integer.parseInt(matcher.group("cardNumber"));
                Card card = ApplicationData.getAllCardsArraylist().get(index);
                System.out.println("name : " + card.getName());
                System.out.println("character : " + card.getCharacter());
                System.out.println("price : " + card.getUpgradeCost());
                System.out.println("damage : " + card.getDamage());
                System.out.println("accuracy : " + card.getAccuracy());
                System.out.println("duration : " + card.getDuration());
                System.out.println("level : " + card.getLevel());
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[5]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[5]));
                matcher.find();
                String[] entered = new String[8];
                entered[0] = matcher.group("name");
                entered[1] = matcher.group("character");
                entered[2] = matcher.group("accurracy");
                entered[3] = matcher.group("duration");
                entered[4] = matcher.group("damage");
                entered[5] = matcher.group("level");
                entered[6] = matcher.group("upgradeCost");
                int index = Integer.parseInt(matcher.group("cardNumber"));
                index--;
                if (entered[0]==null || entered[0].equals("")) entered[0] = ApplicationData.getAllCardsArraylist().get(index).getName();
                if (entered[1]==null || entered[1].equals("")) entered[1] = ApplicationData.getAllCardsArraylist().get(index).getCharacter();
                if (entered[2]==null || entered[2].equals("")) entered[2] = String.valueOf(ApplicationData.getAllCardsArraylist().get(index).getAccuracy());
                if (entered[3]==null || entered[3].equals("")) entered[3] = String.valueOf(ApplicationData.getAllCardsArraylist().get(index).getDuration());
                if (entered[4]==null || entered[4].equals("")) entered[4] = String.valueOf(ApplicationData.getAllCardsArraylist().get(index).getDamage());
                if (entered[5]==null || entered[5].equals("")) entered[5] = String.valueOf(ApplicationData.getAllCardsArraylist().get(index).getLevel());
                if (entered[6]==null || entered[6].equals("")) entered[6] = String.valueOf(ApplicationData.getAllCardsArraylist().get(index).getUpgradeCost());
                if (index >= ApplicationData.getAllCardsArraylist().size())
                    System.out.println("Invalid index");
                else System.out.println(AdminMenuController.editCard(entered, index));
                ejra =  true;
            }
            else if (input.matches(String.valueOf(patterns[6]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[6]));
                matcher.find();
                int index = Integer.parseInt(matcher.group("cardNumber"));
                index--;
                if (index >= ApplicationData.getAllCardsArraylist().size())
                    System.out.println("Invalid index");
                else {
                    System.out.println("Are you sure?(y/n)");
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                        ApplicationData.removeCardFromAllCardsArraylist(index);
                        System.out.println("Card removed successfully!");
                    }
                }
            }
            else if (input.matches(String.valueOf(patterns[7]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[7]));
                matcher.find();
                for (int i = 0; i < ApplicationData.getUserArrayList().size(); i++) {
                    User user = ApplicationData.getUserArrayList().get(i);
                    System.out.println(i+1 + ". name : " + user.getNickname() + " ,Level : " + user.getLevel() + " ,Coins : " + user.getCoins());
                }
                ejra = true;
            }
            if(!ejra) {
                System.out.println("invalid command");
            }
            input = scanner.nextLine();
        }
        return false;
    }
}
