package Model;

import Controller.GameController;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.Math.min;

public class Card {
    private String name;
    private int duration;
    private int accuracy;
    private int damage;
    private int upgradeLevel;
    private String character;
    private int upgradeCost;
    private int level = 0;

    private static String url="jdbc:mysql//localhost:3306/project";
    private static String username="root";
    private static String password="soroush1384";
    private static Connection connection;
    private static java.sql.Statement statement;
    //separ_shafa_powerSupply_hellChanger_
    //maintenance_roundDec_cardKiller_
    //cardInjury_copier_visionLess
//    private User userInUse;
//    private User temporaryUser;

//    private String[] hostRowStatus;
//    private String[] guestRowStatus;

    public Card() {}
    public static void initialize(){
        ApplicationData.newAllCardsArrayList();
        try{
            connection= DriverManager.getConnection(url,username,password);
            statement=connection.createStatement();
        }
        catch (SQLException e){throw new RuntimeException(e);}
        System.out.println("Card Database connected");
        try{
            ResultSet resultSet=statement.executeQuery("SELECT * FROM CARD");
            while(resultSet.next()){
                String name=resultSet.getString(1);
                int dur=resultSet.getInt(2);
                int acc=resultSet.getInt(3);
                int damage=resultSet.getInt(4);
                String character=resultSet.getString(5);
                int upcost=resultSet.getInt(6);
                Card card=new Card(name,dur,acc,damage,character,upcost,1);
                ApplicationData.getAllCardsArraylist().add(card);
            }
        }
        catch (SQLException e){throw new RuntimeException(e);}
    }
    public void addcardToSQL(Card card){
        String name= card.getName();
        int dur=card.getDuration();
        int accuracy=card.getAccuracy();
        int damage= card.getDamage();
        String car=card.getCharacter();
        int upgradeCost=card.getUpgradeCost();
        String query="INSERT INTO CARD VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement prep = connection.prepareStatement(query);
            prep.setString(1, name);
            prep.setInt(2,dur);
            prep.setInt(3,accuracy);
            prep.setInt(4,damage);
            prep.setString(5,car);
            prep.setInt(6,upgradeCost);
            prep.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){throw new RuntimeException(e);}
    }
    public Card(String name, int duration, int accuracy, int damage, String character, int upgradeCost, int level) {
        this.name=name;
        this.duration = duration;
        this.accuracy = accuracy;
        this.damage = damage;
        this.character = character;
        this.upgradeCost = upgradeCost;
        this.level = level;
        if(character.equals("Motreb"))
            this.upgradeLevel = level + 2;
        else if (character.equals("Dalghak"))
            this.upgradeLevel = level + 4;
        else if (character.equals("Divaneh"))
            this.upgradeLevel = level + 3;
        else this.upgradeLevel = level + 1;
    }
    public Card NextLevelCard(){
        if (character.equals("Motreb")) {
            int tempCost = upgradeCost, tempDamage = damage, tempAccuracy = accuracy, tempDuration = duration, tempLevel = this.level + 2;
            tempDuration = min(5, (tempLevel + 1) / 2);
            tempDamage *= tempDuration;
            tempAccuracy = tempAccuracy * (tempLevel - tempDuration + 1);
            tempCost = tempCost * tempLevel * tempLevel;
            return new Card(name, tempDuration, tempAccuracy, tempDamage, character, tempCost, tempLevel);
        }
        else if (character.equals("Dalghak")) {
            int tempCost = upgradeCost, tempDamage = damage, tempAccuracy = accuracy, tempDuration = duration, tempLevel = this.level + 4;
            tempDuration = min(5, (tempLevel - 1) / 4 + 1);
            tempDamage *= tempDuration;
            tempAccuracy = tempAccuracy * (tempLevel - tempDuration + 1);
            tempCost = tempCost * tempLevel * tempLevel;
            return new Card(name, tempDuration, tempAccuracy, tempDamage, character, tempCost, tempLevel);
        }
        else if (character.equals("Divaneh")) {
            int tempCost = upgradeCost, tempDamage = damage, tempAccuracy = accuracy, tempDuration = duration, tempLevel = this.level + 3;
            tempDuration = (tempLevel - 1) / 3 + 1;
            tempDamage *= tempDuration;
            tempAccuracy = tempAccuracy * (tempLevel - tempDuration + 1);
            tempCost = tempCost * tempLevel * tempLevel;
            return new Card(name, tempDuration, tempAccuracy, tempDamage, character, tempCost, tempLevel);
        }
        else {
            int tempCost = upgradeCost, tempDamage = damage, tempAccuracy = accuracy, tempDuration = duration, tempLevel = this.level + 1;
            tempDuration = min(5, tempLevel);
            tempDamage *= tempDuration;
            tempAccuracy = tempAccuracy * (tempLevel - tempDuration + 1);
            tempCost = tempCost * tempLevel * tempLevel;
            return new Card(name, tempDuration, tempAccuracy, tempDamage, character, tempCost, tempLevel); //Guguli
        }
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public static void separInstaller(int index,int player) {
        if (player == 1) {
            Card [] guestRowCards = GameController.getGame().getGuestRowCards();
            String [] guestRowStatus = GameController.getGame().getGuestRowStatus();
            guestRowStatus[index] = "broken";
//            guestRowCards[index] = null;
            GameController.getGame().setGuestRowCards(guestRowCards);
            GameController.getGame().setGuestRowStatus(guestRowStatus);
        }
        else {
            Card [] hostRowCards = GameController.getGame().getHostRowCards();
            String [] hostRowStatus = GameController.getGame().getHostRowStatus();
            hostRowStatus[index] = "broken";
//            hostRowCards[index] = null;
            GameController.getGame().setHostRowCards(hostRowCards);
            GameController.getGame().setHostRowStatus(hostRowStatus);
        }
    }
    public static void HPInstaller(int cardHPScore,int player) {//host=1
        if (player == 1) {
            ApplicationData.getHost().setHP(ApplicationData.getHost().getHP()+cardHPScore);
        }
        else {
            ApplicationData.getGuest().setHP(ApplicationData.getGuest().getHP()+cardHPScore);
        }
    }
    public static void holeChanger(int player,int oldIndex) {
        if (player==1) {
            String [] hostRowStatus = GameController.getGame().getHostRowStatus();
            int index = ApplicationData.getRandom().nextInt(21);
            while (!hostRowStatus[index].equals("nothing"))
                index = ApplicationData.getRandom().nextInt(21);
            hostRowStatus[index] = "hole";hostRowStatus[oldIndex] = "nothing";
            GameController.getGame().setHostRowStatus(hostRowStatus);
        }
        else {
            String [] guestRowStatus = GameController.getGame().getGuestRowStatus();
            int index = ApplicationData.getRandom().nextInt(21);
            while (!guestRowStatus[index].equals("nothing"))
                index = ApplicationData.getRandom().nextInt(21);
            guestRowStatus[index] = "hole";guestRowStatus[oldIndex] = "nothing";
            GameController.getGame().setGuestRowStatus(guestRowStatus);
        }
    }

    public static void holeRemover(int player, int index) {
        if (player==1) {
            String [] hostRowStatus = GameController.getGame().getHostRowStatus();
            hostRowStatus[index] = "nothing";
            GameController.getGame().setHostRowStatus(hostRowStatus);
        }
        else {
            String [] guestRowStatus = GameController.getGame().getGuestRowStatus();
             guestRowStatus[index] = "nothing";
             GameController.getGame().setGuestRowStatus(guestRowStatus);
        }
    }

    public static void roundDecreaseOfPlayer(int player) {
        if (player==1)
            GameController.getGame().setHostRemainingTurns(GameController.getGame().getHostRemainingTurns()-1);
        else GameController.getGame().setGuestRemainingTurns(GameController.getGame().getGuestRemainingTurns()-1);
    }
    public static void powerIncrease(int player) {
        if (player==1) {
            String [] hostRowStatus = GameController.getGame().getHostRowStatus();
            int index = ApplicationData.getRandom().nextInt(21);
            while (!hostRowStatus[index].equals("card")) {
                index = ApplicationData.getRandom().nextInt(21);
            }
            Card [] hostRowCards = GameController.getGame().getHostRowCards();
            hostRowCards[index].setDamage(hostRowCards[index].getDamage()+hostRowCards[index].getDuration());
            hostRowCards[index].setAccuracy(hostRowCards[index].getAccuracy()+3);
            GameController.getGame().setHostRowStatus(hostRowStatus);
        }
        else {
            String [] guestRowStatus = GameController.getGame().getGuestRowStatus();
            int index = ApplicationData.getRandom().nextInt(21);
            while (!guestRowStatus[index].equals("card")) {
                index = ApplicationData.getRandom().nextInt(21);
            }
            Card [] guestRowCards = GameController.getGame().getHostRowCards();
            guestRowCards[index].setDamage(guestRowCards[index].getDamage()+guestRowCards[index].getDuration());
            guestRowCards[index].setAccuracy(guestRowCards[index].getAccuracy()+3);
            GameController.getGame().setGuestRowStatus(guestRowStatus);
        }
    }
    public static void cardRemover(int player) {
        if (player==1) {
            int index = ApplicationData.getRandom().nextInt(GameController.getGame().getGuestCardsAtHand().size());
            Card card = GameController.getGame().getGuestCardsAtHand().get(index);
            GameController.getGame().removeCardFromGuestCardsAtHand(index);
            GameController.getGame().addCardToHostCardsAtHand(card);
        }
        else {
            int index = ApplicationData.getRandom().nextInt(GameController.getGame().getHostCardsAtHand().size());
            Card card = GameController.getGame().getHostCardsAtHand().get(index);
            GameController.getGame().removeCardFromHostCardsAtHand(index);
            GameController.getGame().addCardToGuestCardsAtHand(card);
        }
    }
    public static void cardPowerDecrease(int player) {
        int index1 = ApplicationData.getRandom().nextInt(21);
        int index2 = ApplicationData.getRandom().nextInt(21);
        if (player==1) {
            String [] guestRowStatus = GameController.getGame().getGuestRowStatus();
            Card [] guestRowCards = GameController.getGame().getGuestRowCards();
            while (!guestRowStatus[index1].equals("card")) {
                index1 = ApplicationData.getRandom().nextInt(21);
            }
            while (!guestRowStatus[index1].equals("card") || index2==index1) {
                index2 = ApplicationData.getRandom().nextInt(21);
            }
            guestRowCards[index1].setAccuracy(guestRowCards[index1].getAccuracy()-5);
            guestRowCards[index2].setDamage(guestRowCards[index1].getDamage()- 2*guestRowCards[index1].getDuration());
            GameController.getGame().setGuestRowCards(guestRowCards);
        }
        else {
            String [] hostRowStatus = GameController.getGame().getHostRowStatus();
            Card [] hostRowCards = GameController.getGame().getHostRowCards();
            index1 = ApplicationData.getRandom().nextInt(21);
            index2 = ApplicationData.getRandom().nextInt(21);
            while (!hostRowStatus[index1].equals("card")) {
                index1 = ApplicationData.getRandom().nextInt(21);
            }
            while (!hostRowStatus[index1].equals("card") || index2==index1) {
                index2 = ApplicationData.getRandom().nextInt(21);
            }
            hostRowCards[index1].setAccuracy(hostRowCards[index1].getAccuracy()-5);
            hostRowCards[index2].setDamage(hostRowCards[index1].getDamage()- 2*hostRowCards[index1].getDuration());
            GameController.getGame().setHostRowCards(hostRowCards);
        }
    }
    public static void cardCopier(int player) {
        Scanner scanner = ApplicationData.getScanner();
        System.out.println("Choose the number to copy that : ");
        int index1 = scanner.nextInt();
        if (player==1) {
            ArrayList<Card> hostCardsAtHand = GameController.getGame().getHostCardsAtHand();
            hostCardsAtHand.add(hostCardsAtHand.get(index1));
            GameController.getGame().setHostCardsAtHand(hostCardsAtHand);
        }
        else {
            ArrayList<Card> guestCardsAtHand = GameController.getGame().getGuestCardsAtHand();
            guestCardsAtHand.add(guestCardsAtHand.get(index1));
            GameController.getGame().setGuestCardsAtHand(guestCardsAtHand);
        }
    }
    public static void cardsAtHandHider(int player) {
        if (player==1) {
            ArrayList<Card> hostCardsAtHand = GameController.getGame().getHostCardsAtHand();
            Collections.shuffle(hostCardsAtHand);
            GameController.getGame().setHostCardsAtHand(hostCardsAtHand);
        }
        else {
            ArrayList<Card> guestCardsAtHand = GameController.getGame().getGuestCardsAtHand();
            Collections.shuffle(guestCardsAtHand);
            GameController.getGame().setGuestCardsAtHand(guestCardsAtHand);
        }
    }
    public  boolean isSpecial(){
        String str= this.character;
        if(str.equals("HPInstaller") || str.equals("holeRemover") || str.equals("holeChanger") || str.equals("separInstaller"))
            return true;
        return false;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }
}
