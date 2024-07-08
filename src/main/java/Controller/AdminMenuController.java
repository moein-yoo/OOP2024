package Controller;

import Model.ApplicationData;
import Model.Card;

import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminMenuController {
    public static String addNewCard(String[] entered) {
        String name = entered[0];
        String character = entered[1];
        int accuracy = Integer.parseInt(entered[2]);
        int duration = Integer.parseInt(entered[3]);
        int damage = Integer.parseInt(entered[4]);
        int level = Integer.parseInt(entered[5]);
        int upgradeCost = Integer.parseInt(entered[6]);
        for (Card card : ApplicationData.getAllCardsArraylist()) {
            if (card.getName().equals(name)) {
                return "The name is already exist!";
            }
        }
        if (!character.equals("Motreb") || !character.equals("Dalghak") || !character.equals("Divaneh") || !character.equals("Guguli")) {
            return "Character type is incorrect";
        }
        else if (accuracy > 100 || accuracy < 10)
            return "Accuracy should be 9 < accuracy < 101 ";
        else if (duration > 5 || duration < 1)
            return "Duration should be 0 < duration < 6";
        else if (damage > 50 || damage < 10)
            return "Damage should be 9 < damage < 51";
        else {
            //(String name, int duration, int accuracy, int damage, String character, int upgradeCost, int level)
            ApplicationData.addCardToAllCardsArraylist(new Card(name, duration, accuracy, damage, character, upgradeCost, level));
            Card.addcardToSQL(new Card(name, duration, accuracy, damage, character, upgradeCost, level));
            return "The Card added to the list successfully";
        }
    }
    public static String editCard(String[] entered, int index, boolean changeName) {
        String name = entered[0];
        String character = entered[1];
        int accuracy = Integer.parseInt(entered[2]);
        int duration = Integer.parseInt(entered[3]);
        int damage = Integer.parseInt(entered[4]);
        int level = Integer.parseInt(entered[5]);
        int upgradeCost = Integer.parseInt(entered[6]);
        for (Card card : ApplicationData.getAllCardsArraylist()) {
            if (card.getName().equals(name)) {
                return "The name is already exist!";
            }
        }
        if (!character.equals("Motreb") || !character.equals("Dalghak") || !character.equals("Divaneh") || !character.equals("Guguli")) {
            return "Character type is incorrect";
        }
        else if (accuracy > 100 || accuracy < 10)
            return "Accuracy should be 9 < accuracy < 101 ";
        else if (duration > 5 || duration < 1)
            return "Duration should be 0 < duration < 6";
        else if (damage > 50 || damage < 10)
            return "Damage should be 9 < damage < 51";
        else {
            //(String name, int duration, int accuracy, int damage, String character, int upgradeCost, int level)
            System.out.println("Are you sure?(y/n)");
            Scanner scanner = ApplicationData.getScanner();
            String inp = scanner.nextLine();
            if (inp.equalsIgnoreCase("y") || inp.equalsIgnoreCase("yes")) {
                if (changeName) {
                    Card.modifyCardName(name, ApplicationData.getAllCardsArraylist().get(index).getName());
                }
                ApplicationData.setCardInAllCardsArraylist(index, new Card(name, duration, accuracy, damage, character, upgradeCost, level));
                Card.modifyCardInSQL(new Card(name, duration, accuracy, damage, character, upgradeCost, level));
            }
            return "The Card edited successfully";
        }
    }
}
