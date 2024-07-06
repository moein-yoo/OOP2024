package Model;

import java.sql.*;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String passwordRecoveryQuestion;
    private int passwordRecoveryType;
    private int hp;
    private int coins;
    private ArrayList<Card> allPossessedCards;
    private ArrayList<Card> twentyCardsAtDeck;
    private String character;//1-2-3-4
    private int level;
    private int xp;
    private static String url="jdbc:mysql//localhost:3306/project";
    private static String Username="root";
    private static String Password="soroush1384";
    private static Connection connection;
    private static java.sql.Statement statement;
    public User (String username, String nickname, String password, String email, String passwordRecoveryQuestion, int passwordRecoveryType, String character){
        this.passwordRecoveryType = passwordRecoveryType;
        this.character=character;
        this.xp=0;
        this.level=1;
        this.hp=100;
        this.coins=100;
        this.username =username;
        this.password =password;
        this.nickname=nickname;
        this.email=email;
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
        this.allPossessedCards =new ArrayList<>();
        StarterPack(this.allPossessedCards);
        this.twentyCardsAtDeck=new ArrayList<>();
        twentyCardsAtDeck.addAll(allPossessedCards);
    }
    public static void initialize(){
        ApplicationData.newUserArrayList();
        try{
            connection= DriverManager.getConnection(url,Username,Password);
            statement=connection.createStatement();
        }
        catch (SQLException e){throw new RuntimeException(e);}
        System.out.println("User Database connected");
        try{
            ResultSet resultSet=statement.executeQuery("SELECT * FROM USER");
            while(resultSet.next()){
                String username=resultSet.getString(1);
                String password=resultSet.getString(2);
                String nickname=resultSet.getString(3);
                String email=resultSet.getString(4);
                String passwordRecoveryQuestion=resultSet.getString(5);
                int passwordRecoveryType=resultSet.getInt(6);
                int hp=resultSet.getInt(7);
                int coins=resultSet.getInt(8);
                int character=resultSet.getInt(9);
                int level=resultSet.getInt(10);
                int xp=resultSet.getInt(11);
                String allPossesed=resultSet.getString(12);
                String twenty=resultSet.getString(13);
                ArrayList<Card> allPos=new ArrayList<>();

                ArrayList<Card> twen=new ArrayList<>();
                String charType = "";
                if (character == 1)
                    charType = "Motreb";
                if (character == 2)
                    charType = "Dalghak";
                if (character == 3)
                    charType = "Divaneh";
                if (character == 4)
                    charType = "Guguli";
                User user=new User(username,nickname,password,email,passwordRecoveryQuestion,passwordRecoveryType,charType);
                user.setXp(xp);
                user.setLevel(level);
                user.setHP(hp);
                user.setCoins(coins);
                ApplicationData.getUserArrayList().add(user);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void StarterPack(ArrayList<Card> a){
        //fill arraylist with 20 cards
    }
    public ArrayList<Card> getAllPossessedCards(){return this.allPossessedCards;}
    public void setTwentyCardsAtDeck(ArrayList<Card> a){
        this.twentyCardsAtDeck=new ArrayList<>();
        for(int i=0;i<20;i++){
            this.twentyCardsAtDeck.add(a.get(i));
        }
    }
    public ArrayList<Card> getTwentyCardsAtDeck(){return this.twentyCardsAtDeck;}
    public void buyCard(Card i){
        //age mikhay barresi kon card az ghabl mojoode ya na,age mojoode update she va...
        this.allPossessedCards.add(i);
    }
    public void setHP(int HP) {
        this.hp = HP;
    }
    public void setUsername(String username){
        this.username =username;
    }
    public void setPassword(String password){
        this.password =password;
    }
    public void setNickname(String nickname){
        this.nickname=nickname;
    }
    public void setEmail(String Email){
        this.email=email;
    }
    public void setPasswordRecoveryQuestion(String recoveryQuestion){
        this.passwordRecoveryQuestion =recoveryQuestion;
    }
    public void setCoins(int coins){
        this.coins=coins;
    }

    public void increaseXP(int increase){
        this.xp+=increase;
        while(xp>level*100){
            xp-=level*100;
            level++;
        }
    }
    public int getHP() {
        return this.hp;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getNickname(){
        return this.nickname;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPasswordRecoveryQuestion(){
        return this.passwordRecoveryQuestion;
    }
    public int getCoins(){
        return this.coins;
    }
    public int getLevel(){
        return this.level;
    }
    public int getXp(){
        return this.xp;
    }
    public void setXp(int xp){this.xp=xp;}
    public void setLevel(int level){this.level=level;}

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getPasswordRecoveryType() {
        return passwordRecoveryType;
    }

    public void setPasswordRecoveryType(int passwordRecoveryType) {
        this.passwordRecoveryType = passwordRecoveryType;
    }
}
