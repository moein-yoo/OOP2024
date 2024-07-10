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
    private String character = "nothing";//1-2-3-4
    private int level;
    private int xp;
    private static String url="jdbc:mysql://localhost:3306/project";
    private static String Username="root";
//    static String Password="soroush1384";
    static String Password="@9984moeiN";
    private static Connection connection;
    private static java.sql.Statement statement;
    public User (String username, String nickname, String password, String email, String passwordRecoveryQuestion, int passwordRecoveryType){
        this.passwordRecoveryType = passwordRecoveryType;
        this.character=null;
        this.xp=0;
        this.level=1;
        this.hp=100;
        this.coins=20;
        this.username =username;
        this.password =password;
        this.nickname=nickname;
        this.email=email;
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
        this.allPossessedCards = StarterPack(this.allPossessedCards);;

    }
    public User (String username, String nickname, String password, String email, String passwordRecoveryQuestion, int passwordRecoveryType,String character){
        this.passwordRecoveryType = passwordRecoveryType;
        this.character=character;
        this.xp=0;
        this.level=1;
        this.hp=100;
        this.coins=20;
        this.username =username;
        this.password =password;
        this.nickname=nickname;
        this.email=email;
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
        this.allPossessedCards = StarterPack(this.allPossessedCards);
    }

    public User() {
        //nothing
    }

    public static void initialize() throws SQLException {
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
                int level=resultSet.getInt(9);
                int xp=resultSet.getInt(10);
                String allPossesed=resultSet.getString(11);

                ArrayList<Card> allPos=new ArrayList<>();

                String [] str1=allPossesed.split("#");
                ArrayList<String> names1=new ArrayList<>();
                ArrayList<Integer> levels1=new ArrayList<>();
                for(int i=0;i< str1.length;i++){
                    if(i%2==0)
                        names1.add(str1[i]);
                    if(i%2==1)
                        levels1.add(Integer.parseInt(str1[i]));
                }
                for(int i=0;i< names1.size();i++){
                    allPos.add(ApplicationData.cardTranslator(names1.get(i),levels1.get(i)));
                }


                User user=new User(username,nickname,password,email,passwordRecoveryQuestion,passwordRecoveryType);
                user.setXp(xp);
                user.setLevel(level);
                user.setHP(hp);
                user.setCoins(coins);
                user.setAllPossessedCards(allPos);
                ApplicationData.getUserArrayList().add(user);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        connection.setAutoCommit(false);
    }
    public static void addNewUserToSQL(User user){
        String username=user.getUsername();
        String password=user.getPassword();
        String nickname=user.getNickname();
        String email=user.getEmail();
        String passwordRecoveryQuestion=user.getPasswordRecoveryQuestion();
        int passwordRecoveryType=user.getPasswordRecoveryType();
        int hp= user.getHP();
        int coins=user.getCoins();
        int level=user.getLevel();
        int xp=user.getXp();
        ArrayList<Card>allPos=user.getAllPossessedCards();
        StringBuilder stringBuilder=new StringBuilder();
        boolean first=true;
        for(Card card:allPos){
            if(!first)
                stringBuilder.append("#");
            stringBuilder.append(card.getName());
            stringBuilder.append("#");
            stringBuilder.append(card.getLevel());
            first=false;
        }

        String allPossessed=stringBuilder.toString();

        String query2="INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(query2);

            preparedStatement=connection.prepareStatement(query2);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,nickname);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,passwordRecoveryQuestion);
            preparedStatement.setInt(6,passwordRecoveryType);
            preparedStatement.setInt(7,hp);
            preparedStatement.setInt(8,coins);
            preparedStatement.setInt(9,level);
            preparedStatement.setInt(10,xp);
            preparedStatement.setString(11,allPossessed);

            preparedStatement.executeUpdate();
            connection.commit();

        }
        catch (SQLException e){throw new RuntimeException(e);}

    }
    public static void updateUserInSQL(User user){
        String username=user.getUsername();
        String password=user.getPassword();
        String nickname=user.getNickname();
        String email=user.getEmail();
        String passwordRecoveryQuestion=user.getPasswordRecoveryQuestion();
        int passwordRecoveryType=user.getPasswordRecoveryType();
        int hp= user.getHP();
        int coins=user.getCoins();
        int level=user.getLevel();
        int xp=user.getXp();
        ArrayList<Card>allPos=user.getAllPossessedCards();
        StringBuilder stringBuilder=new StringBuilder();
        boolean first=true;
        for(Card card:allPos){
            if(!first)
                stringBuilder.append("#");
            stringBuilder.append(card.getName());
            stringBuilder.append("#");
            stringBuilder.append(card.getLevel());
            first=false;
        }

        String allPossessed=stringBuilder.toString();


        String query1="DELETE FROM USER WHERE username= ?";
        String query2="INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(query1);
            preparedStatement.setString(1,user.username);
            preparedStatement.executeUpdate();
            connection.commit();

            preparedStatement=connection.prepareStatement(query2);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,nickname);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,passwordRecoveryQuestion);
            preparedStatement.setInt(6,passwordRecoveryType);
            preparedStatement.setInt(7,hp);
            preparedStatement.setInt(8,coins);
            preparedStatement.setInt(9,level);
            preparedStatement.setInt(10,xp);
            preparedStatement.setString(11,allPossessed);

            preparedStatement.executeUpdate();
            connection.commit();

        }
        catch (SQLException e){throw new RuntimeException(e);}

    }
    public static void deleteAccountInSQL(User user){
        String username= user.getUsername();
        String query="DELETE FROM USER WHERE username= ?";
        try {
            PreparedStatement prep=connection.prepareStatement(query);
            prep.setString(1,username);
            prep.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){throw new RuntimeException(e);}

    }
    public ArrayList<Card> StarterPack(ArrayList<Card> a){
        a=new ArrayList<>();
        ArrayList<Card> sample=new ArrayList<>();
        sample.addAll(ApplicationData.getAllCardsArraylist());
        for(int i=0;i<15;i++){
            int r=ApplicationData.getRandom().nextInt(sample.size());
            a.add(sample.get(r));
            sample.remove(r);
        }
        return a;
    }
    public ArrayList<Card> getAllPossessedCards(){return this.allPossessedCards;}
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
            coins+=10*level;
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
    public void setAllPossessedCards(ArrayList<Card> allPossessedCards){this.allPossessedCards=allPossessedCards;}
    public void addToPossessedCards(Card card){this.allPossessedCards.add(card);}
    public void removeFromPossessedCards(Card card){this.allPossessedCards.remove(card);}
}
