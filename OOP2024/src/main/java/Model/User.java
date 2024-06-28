package Model;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String passwordRecoveryQuestion;
    private int hp;
    private int coins;
    private ArrayList<Card> cards;
    private int character;//1-2-3-4
    private int level;
    private int xp;
    public User (String username, String nickname, String password, String email, String passwordRecoveryQuestion, int character){
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
        this.cards=new ArrayList<>();
        StarterPack(this.cards);
    }
    public void StarterPack(ArrayList<Card> a){
        //fill arraylist with 20 cards
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


}
