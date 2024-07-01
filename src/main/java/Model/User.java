package Model;

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
    private int character;//1-2-3-4
    private int level;
    private int xp;
    public User (String username, String nickname, String password, String email, String passwordRecoveryQuestion, int passwordRecoveryType, int character){
        this.passwordRecoveryType = passwordRecoveryType;
        this.character=character;
        this.xp=0;
        this.level=1;
        this.hp=100;
        this.coins=100;
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.email=email;
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
        this.allPossessedCards =new ArrayList<>();
        StarterPack(this.allPossessedCards);
        this.twentyCardsAtDeck=new ArrayList<>();
        twentyCardsAtDeck.addAll(allPossessedCards);
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
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
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
    public int setCoins(){
        return this.coins;
    }
    public int getLevel(){
        return this.level;
    }
    public int getXp(){
        return this.xp;
    }


    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public int getPasswordRecoveryType() {
        return passwordRecoveryType;
    }

    public void setPasswordRecoveryType(int passwordRecoveryType) {
        this.passwordRecoveryType = passwordRecoveryType;
    }
}
