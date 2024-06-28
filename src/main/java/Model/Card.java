package Model;

public class Card {
    private int duration;
    private int accuracy;
    private int damage;
    private String kind;
    private int HP = 0;
    //separ_shafa_powerSupply_hellChanger_
    //maintenance_roundDec_cardKiller_
    //cardInjury_copier_visionLess
    private User userInUse;
    private User temporaryUser;
    private Card[] attackerUsersCardList;
    private Card[] enemyUsersCardList;
    private String[] attackerUsersListString;
    private String[] enemyUsersListString;

    public Card(int duration, int accuracy, int damage, String kind, int HP) {
        this.duration = duration;
        this.accuracy = accuracy;
        this.damage = damage;
        this.kind = kind;
        this.HP = HP;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void separInstaller(int index,int player) {
        if (player == 1) {
            enemyUsersCardList = Application.getEnemyUsersCardList();
            enemyUsersListString = Application.getEnemyUsersListString();
            enemyUsersListString[index] = "shekaste";
            enemyUsersCardList[index] = null;
            Application.setEnemyUsersCardList(enemyUsersCardList);
            Application.setEnemyUsersListString(enemyUsersListString);
        }
        else {
            attackerUsersCardList = Application.getAttackerUsersCardList();
            attackerUsersListString = Application.getAttackerUsersListString();
            attackerUsersListString[index] = "shekaste";
            attackerUsersCardList[index] = null;
            Application.setAttackerUsersCardList(attackerUsersCardList);
            Application.setAttackerUsersListString(attackerUsersListString);
        }
    }
    public void HPInstaller(int cardHPScore,int player) {
        if (player == 1) {
            Application.getUserInUse().setHP(Application.getUserInUse().getHP()+cardHPScore);
        }
        else {
            Application.getTemporaryUser().setHP(Application.getTemporaryUser().getHP()+cardHPScore);
        }
    }
}
