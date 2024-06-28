package Model;

public class Application {
    private static User userInUse;
    private static User temporaryUser;
    private static Card[] attackerUsersCardList;
    private static Card[] enemyUsersCardList;
    private static String[] attackerUsersListString;
    private static String[] enemyUsersListString;

    public static User getUserInUse() {
        return userInUse;
    }

    public static void setUserInUse(User userInUse) {
        Application.userInUse = userInUse;
    }

    public static User getTemporaryUser() {
        return temporaryUser;
    }

    public static void setTemporaryUser(User temporaryUser) {
        Application.temporaryUser = temporaryUser;
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
}
