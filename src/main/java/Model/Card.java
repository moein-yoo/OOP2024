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

     static String url="jdbc:mysql://localhost:3306/project";
     //static String url = "C:\\Users\\moein\\AppData\\Roaming\\JetBrains\\IntelliJIdea2023.3\\jdbc-drivers\\MySQL ConnectorJ\\8.2.0\\com\\mysql\\mysql-connector-j\\8.2.0\\mysql-connector-j-8.2.0.jar";
     static String username="root";
     static String password="@9984moeiN";
     //static String password="soroush1384";
     static Connection connection;
     static java.sql.Statement statement;
    //separ_shafa_powerSupply_hellChanger_
    //maintenance_roundDec_cardKiller_
    //cardInjury_copier_visionLess
//    private User userInUse;
//    private User temporaryUser;

//    private String[] hostRowStatus;
//    private String[] guestRowStatus;

    public Card() {}
    public static void initialize() throws SQLException {
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
        connection.setAutoCommit(false);
    }
    public static void addcardToSQL(Card card){
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
    public static void modifyCardInSQL(Card card){
        String query="UPDATE CARD SET duration=?,accuracy=?,damage=?,charac=?,upgradeCost=? WHERE name = ?";
        int dur=card.getDuration();int acc=card.getAccuracy();int dam=card.getDamage();String character= card.getCharacter();
        int upcost=card.getUpgradeCost(); String name=card.getName();
        try{
            PreparedStatement prep=connection.prepareStatement(query);
            prep.setString(6,name);
            prep.setInt(1,dur);
            prep.setInt(2,acc);
            prep.setInt(3,dam);
            prep.setString(4,character);
            prep.setInt(5,upcost);
            prep.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){throw new RuntimeException(e);}
    }
    public static void modifyCardName(String newName,String oldName){
        String query="UPDATE CARD SET name=? WHERE name = ?";
        try{
            PreparedStatement prep=connection.prepareStatement(query);
            prep.setString(1,newName);
            prep.setString(2,oldName);
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


    public boolean isSpecial(){    String str= this.character;
        if(str.equalsIgnoreCase("HPInstaller")
                || str.equalsIgnoreCase("holeRemover")
                || str.equalsIgnoreCase("holeChanger")
                || str.equalsIgnoreCase("separInstaller"))
            return true;
        if(str.equalsIgnoreCase("roundDec")
                || str.equalsIgnoreCase("powerIncrease"))
            return true;
        if(str.equalsIgnoreCase("cardRemover")
                || str.equalsIgnoreCase("cardPowerDecrease"))
            return true;
        if(str.equalsIgnoreCase("cardCopier")
                || str.equalsIgnoreCase("cardAtHandHider"))
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
