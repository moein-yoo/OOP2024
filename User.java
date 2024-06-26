package model;

import java.util.ArrayList;

public class User {
    String username;
    String password;
    String nickname;
    String email;
    String passwordrecoveryquestion;
    int hp;
    int coins;
    ArrayList<Card> cards;
    int character;//1-2-3-4
    int level;
    int xp;
    User (String username,String nickname,String password,String email,String passwordrecoveryquestion,int character){
        this.character=character;
        this.xp=0;
        this.level=1;
        this.hp=100;
        this.coins=100;
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.email=email;
        this.passwordrecoveryquestion=passwordrecoveryquestion;
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
        this.passwordrecoveryquestion=recoveryQuestion;
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
        return this.passwordrecoveryquestion;
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
