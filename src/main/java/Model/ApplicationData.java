package Model;

import Controller.GameController;
import ViewGraphic.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.min;

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

    //Graphic Addition
    private static Stage stage;
    private static LoginMenu loginMenu;
    private static ProfileMenu profileMenu;
    private static SignUpMenu signUpMenu;
    private static MainMenu mainMenu;
    //


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
    public static void addCardToAllCardsArraylist(Card card) {
        ApplicationData.allCardsArraylist.add(card);
    }
    public static void removeCardFromAllCardsArraylist(int index) {
        ApplicationData.allCardsArraylist.remove(index);
    }
    public static void setCardInAllCardsArraylist(int index, Card card) {
        ApplicationData.allCardsArraylist.set(index, card);
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        ApplicationData.game = game;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ApplicationData.stage = stage;
    }

    public static LoginMenu getLoginMenu() {
        return loginMenu;
    }

    public static void setLoginMenu(LoginMenu loginMenu) {
        ApplicationData.loginMenu = loginMenu;
    }

    public static ProfileMenu getProfileMenu() {
        return profileMenu;
    }

    public static void setProfileMenu(ProfileMenu profileMenu) {
        ApplicationData.profileMenu = profileMenu;
    }

    public static SignUpMenu getSignUpMenu() {
        return signUpMenu;
    }

    public static void setSignUpMenu(SignUpMenu signUpMenu) {
        ApplicationData.signUpMenu = signUpMenu;
    }

    public static MainMenu getMainMenu() {
        return mainMenu;
    }

    public static void setMainMenu(MainMenu mainMenu) {
        ApplicationData.mainMenu = mainMenu;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
    public static void newUserArrayList(){userArrayList=new ArrayList<>();}
    public static void newAllCardsArrayList(){allCardsArraylist=new ArrayList<>();}

    public static Card cardTranslator(String name, int cardLevel) {
        String character = "";
        int tempCost = 0, tempDamage = 0, tempAccuracy = 0, tempDuration = 1;
        for (Card card : ApplicationData.getAllCardsArraylist()) {
            if (card.getName().equals(name)) {
                character = card.getCharacter();
                tempCost = card.getUpgradeCost();
                tempDamage = card.getDamage();
                tempAccuracy = card.getAccuracy();
                tempDuration = card.getDuration();
                break;
            }
        }

        if (character.equals("Motreb")) {
            tempDuration = min(5, (cardLevel + 1) / 2);
            tempDamage *= tempDuration;
            tempAccuracy = tempAccuracy * (cardLevel - tempDuration + 1);
            tempCost = tempCost * cardLevel * cardLevel;
            return new Card(name, tempDuration, tempAccuracy, tempDamage, character, tempCost, cardLevel);
        }
        else if (character.equals("Dalghak")) {
            tempDuration = min(5, (cardLevel - 1) / 4 + 1);
            tempDamage *= tempDuration;
            tempAccuracy = tempAccuracy * (cardLevel - tempDuration + 1);
            tempCost = tempCost * cardLevel * cardLevel;
            return new Card(name, tempDuration, tempAccuracy, tempDamage, character, tempCost, cardLevel);
        }
        else if (character.equals("Divaneh")) {
            tempDuration = (cardLevel - 1) / 3 + 1;
            tempDamage *= tempDuration;
            tempAccuracy = tempAccuracy * (cardLevel - tempDuration + 1);
            tempCost = tempCost * cardLevel * cardLevel;
            return new Card(name, tempDuration, tempAccuracy, tempDamage, character, tempCost, cardLevel);
        }
        else {
            tempDuration = min(5, cardLevel);
            tempDamage *= tempDuration;
            tempAccuracy = tempAccuracy * (cardLevel - tempDuration + 1);
            tempCost = tempCost * cardLevel * cardLevel;
            return new Card(name, tempDuration, tempAccuracy, tempDamage, character, tempCost, cardLevel); //Guguli
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
