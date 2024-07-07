package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.min;
import static java.lang.Math.pow;

public class ApplicationData {
    private static ArrayList<User> userArrayList;
    private static ArrayList<Card> allCardsArraylist;
    private static Scanner scanner = new Scanner(System.in);
    private static User host;
    private static User guest;
    private static Card[] attackerUsersCardList;
    private static Card[] enemyUsersCardList;
    private static String[] attackerUsersListString;
    private static String[] enemyUsersListString;
    private static Random random = new Random();
    private static boolean betStatus;
    private static int betCost;
    private static boolean secondLogin = false;
    private static Game game;


    public static User getHost() {
        return host;
    }

    public static void setHost(User host) {
        ApplicationData.host = host;
    }

    public static User getGuest() {
        return guest;
    }

    public static void setGuest(User temporaryUser) {
        ApplicationData.guest = temporaryUser;
    }

    public static Card[] getAttackerUsersCardList() {
        return attackerUsersCardList;
    }

    public static void setAttackerUsersCardList(Card[] attackerUsersCardList) {
        ApplicationData.attackerUsersCardList = attackerUsersCardList;
    }

    public static Card[] getEnemyUsersCardList() {
        return enemyUsersCardList;
    }

    public static void setEnemyUsersCardList(Card[] enemyUsersCardList) {
        ApplicationData.enemyUsersCardList = enemyUsersCardList;
    }

    public static String[] getAttackerUsersListString() {
        return attackerUsersListString;
    }

    public static void setAttackerUsersListString(String[] attackerUsersListString) {
        ApplicationData.attackerUsersListString = attackerUsersListString;
    }

    public static String[] getEnemyUsersListString() {
        return enemyUsersListString;
    }

    public static void setEnemyUsersListString(String[] enemyUsersListString) {
        ApplicationData.enemyUsersListString = enemyUsersListString;
    }
    public static void decreaseHostHP(int loss){host.setHP(host.getHP()-loss);}
    public static void decreaseGuestHP(int loss){guest.setHP(guest.getHP()-loss);}


    public static Random getRandom() {
        return random;
    }

    public static ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public static void setUserArrayList(ArrayList<User> userArrayList) {
        ApplicationData.userArrayList = userArrayList;
    }
    public static void addToUserArrayList(User user) {
        ApplicationData.userArrayList.add(user);
    }
    public static void removeFromUserArrayList(User user) {
        ApplicationData.userArrayList.remove(user);
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        ApplicationData.scanner = scanner;
    }

    public static boolean isBetStatus() {
        return betStatus;
    }

    public static void setBetStatus(boolean betStatus) {
        ApplicationData.betStatus = betStatus;
    }

    public static int getBetCost() {
        return betCost;
    }

    public static void setBetCost(int betCost) {
        ApplicationData.betCost = betCost;
    }

    public static boolean isSecondLogin() {
        return secondLogin;
    }

    public static void setSecondLogin(boolean secondLogin) {
        ApplicationData.secondLogin = secondLogin;
    }

    public static ArrayList<Card> getAllCardsArraylist() {
        return allCardsArraylist;
    }

    public static void setAllCardsArraylist(ArrayList<Card> allCardsArraylist) {
        ApplicationData.allCardsArraylist = allCardsArraylist;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        ApplicationData.game = game;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
    public static void newUserArrayList(){userArrayList=new ArrayList<>();}
    public static void newAllCardsArrayList(){allCardsArraylist=new ArrayList<>();}

    public static Card cardTranslator(String name, int cardLevel) {
        int cost = 0, damage = 0, accuracy = 0, duration = 1;
        //Card card = new Card();
        switch (name) {
            case "Yazd" -> {
                duration = min(5, (cardLevel + 1) / 2);
                damage = duration * 5;
                accuracy = 10 * (cardLevel - duration + 1);
                cost = 5 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Motreb", cost, cardLevel); //Motreb
            }
            case "Kermanshah" -> {
                duration = min(5, (cardLevel + 1) / 2);
                damage = duration * 7;
                accuracy = 13 * (cardLevel - duration + 1);
                cost = 7 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Motreb", cost, cardLevel); //Motreb
            }
            case "Kerman" -> {
                duration = min(5, (cardLevel + 1) / 2);
                damage = duration * 9;
                accuracy = 15 * (cardLevel - duration + 1);
                cost = 8 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Motreb", cost, cardLevel); //Motreb
            }
            case "Tehran" -> {
                duration = min(5, (cardLevel - 1) / 4 + 1);
                damage = duration * 5;
                accuracy = 10 * (cardLevel - duration + 1);
                cost = 5 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Dalghak", cost, cardLevel);
            }
            case "Tabriz" -> {
                duration = min(5, (cardLevel - 1) / 4 + 1);
                damage = duration * 7;
                accuracy = 13 * (cardLevel - duration + 1);
                cost = 7 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Motreb", cost, cardLevel);
            }
            case "Isfahan" -> {
                duration = min(5, (cardLevel - 1) / 4 + 1);
                damage = duration * 9;
                accuracy = 15 * (cardLevel - duration + 1);
                cost = 8 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Motreb", cost, cardLevel);
            }
            case "Mashhad" -> {
                duration = min(5, (cardLevel - 1) / 3 + 1);
                damage = duration * 5;
                accuracy = 10 * (cardLevel - duration + 1);
                cost = 5 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Divaneh", cost, cardLevel);
            }
            case "Ahwaz" -> {
                duration = min(5, (cardLevel - 1) / 3 + 1);
                damage = duration * 7;
                accuracy = 13 * (cardLevel - duration + 1);
                cost = 7 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Divaneh", cost, cardLevel);
            }
            case "Zahedan" -> {
                duration = min(5, (cardLevel - 1) / 3 + 1);
                damage = duration * 9;
                accuracy = 15 * (cardLevel - duration + 1);
                cost = 8 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Divaneh", cost, cardLevel);
            }
            case "Rasht" -> {
                duration = min(5, cardLevel);
                damage = duration * 5;
                accuracy = 10 * (cardLevel - duration + 1);
                cost = 5 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Guguli", cost, cardLevel);
            }
            case "Bushehr" -> {
                duration = min(5, cardLevel);
                damage = duration * 7;
                accuracy = 13 * (cardLevel - duration + 1);
                cost = 7 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Guguli", cost, cardLevel);
            }
            default -> { // "Gorgan"
                duration = min(5, cardLevel);
                damage = duration * 9;
                accuracy = 15 * (cardLevel - duration + 1);
                cost = 8 * cardLevel * cardLevel;
                return new Card(name, duration, accuracy, damage, "Guguli", cost, cardLevel);  //if (name.equals("Gorgan")) {
            }
        }
    }
    public static boolean cardExists(Card card){
        boolean exists=false;
        for(Card a:allCardsArraylist){
            if(a.getName().equals(card.getName()))
                exists=true;
        }
        return exists;
    }
}
