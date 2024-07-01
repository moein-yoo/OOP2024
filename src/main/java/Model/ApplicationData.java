package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ApplicationData {
    private static ArrayList<User> userArrayList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User host;
    private static User guest;
    private static Card[] attackerUsersCardList;
    private static Card[] enemyUsersCardList;
    private static String[] attackerUsersListString;
    private static String[] enemyUsersListString;
    private static Random random = new Random();


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

    public void setRandom(Random random) {
        this.random = random;
    }
}
