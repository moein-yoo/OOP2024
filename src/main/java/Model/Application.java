package Model;

import java.util.Random;

public class Application {
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

    public static void setUserInUse(User userInUse) {
        Application.host = userInUse;
    }

    public static User getGuest() {
        return guest;
    }

    public static void setGuest(User temporaryUser) {
        Application.guest = temporaryUser;
    }

    public static Card[] getAttackerUsersCardList() {
        return attackerUsersCardList;
    }

    public static void setAttackerUsersCardList(Card[] attackerUsersCardList) {
        Application.attackerUsersCardList = attackerUsersCardList;
    }

    public static Card[] getEnemyUsersCardList() {
        return enemyUsersCardList;
    }

    public static void setEnemyUsersCardList(Card[] enemyUsersCardList) {
        Application.enemyUsersCardList = enemyUsersCardList;
    }

    public static String[] getAttackerUsersListString() {
        return attackerUsersListString;
    }

    public static void setAttackerUsersListString(String[] attackerUsersListString) {
        Application.attackerUsersListString = attackerUsersListString;
    }

    public static String[] getEnemyUsersListString() {
        return enemyUsersListString;
    }

    public static void setEnemyUsersListString(String[] enemyUsersListString) {
        Application.enemyUsersListString = enemyUsersListString;
    }
    public static void decreaseHostHP(int loss){host.setHP(host.getHP()-loss);}
    public static void decreaseGuestHP(int loss){guest.setHP(guest.getHP()-loss);}


    public static Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
