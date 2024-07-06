package Model;

import Controller.GameController;

public class Card {
    private String name;
    private int duration;
    private int accuracy;
    private int damage;
    private int upgradeLevel;
    private String character;
    private int upgradeCost;
    private int level = 0;
    //separ_shafa_powerSupply_hellChanger_
    //maintenance_roundDec_cardKiller_
    //cardInjury_copier_visionLess
    private User userInUse;
    private User temporaryUser;
    private Card[] hostRowCards;
    private Card[] guestRowCards;
    private String[] hostRowStatus;
    private String[] guestRowStatus;

    public Card() {}

    public Card(String name, int duration, int accuracy, int damage, String character, int upgradeCost, int level) {
        this.name=name;
        this.duration = duration;
        this.accuracy = accuracy;
        this.damage = damage;
        this.character = character;
        this.upgradeCost = upgradeCost;
        this.level = level;
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

    public void separInstaller(int index,int player) {
        if (player == 1) {
            guestRowCards = GameController.getGame().getGuestRowCards();
            guestRowStatus = GameController.getGame().getGuestRowStatus();
            guestRowStatus[index] = "broken";
//            guestRowCards[index] = null;
            GameController.getGame().setGuestRowCards(guestRowCards);
            GameController.getGame().setGuestRowStatus(guestRowStatus);
        }
        else {
            hostRowCards = GameController.getGame().getHostRowCards();
            hostRowStatus = GameController.getGame().getHostRowStatus();
            hostRowStatus[index] = "broken";
//            hostRowCards[index] = null;
            GameController.getGame().setHostRowCards(hostRowCards);
            GameController.getGame().setHostRowStatus(hostRowStatus);
        }
    }
    public void HPInstaller(int cardHPScore,int player) {//host=1
        if (player == 1) {
            ApplicationData.getHost().setHP(ApplicationData.getHost().getHP()+cardHPScore);
        }
        else {
            ApplicationData.getGuest().setHP(ApplicationData.getGuest().getHP()+cardHPScore);
        }
    }
    public void holeChanger(int player,int oldIndex) {
        if (player==1) {
            hostRowStatus = GameController.getGame().getHostRowStatus();
            int index = ApplicationData.getRandom().nextInt(21);
            while (!hostRowStatus[index].equals("nothing"))
                index = ApplicationData.getRandom().nextInt(21);
            hostRowStatus[index] = "hole";hostRowStatus[oldIndex] = "nothing";
            GameController.getGame().setHostRowStatus(hostRowStatus);
        }
        else {
            guestRowStatus = GameController.getGame().getGuestRowStatus();
            int index = ApplicationData.getRandom().nextInt(21);
            while (!guestRowStatus[index].equals("nothing"))
                index = ApplicationData.getRandom().nextInt(21);
            guestRowStatus[index] = "hole";guestRowStatus[oldIndex] = "nothing";
            GameController.getGame().setGuestRowStatus(guestRowStatus);
        }
    }

    public void holeRemover(int player, int index) {
        if (player==1) {
            hostRowStatus = GameController.getGame().getHostRowStatus();
            hostRowStatus[index] = "nothing";
            GameController.getGame().setHostRowStatus(hostRowStatus);
        }
        else {
            guestRowStatus = GameController.getGame().getGuestRowStatus();
             guestRowStatus[index] = "nothing";
            GameController.getGame().setGuestRowStatus(guestRowStatus);
        }
    }

    public void roundDecreaseOfPlayer(int player) {
        if (player==1)
            GameController.getGame().setHostRemainingTurns(GameController.getGame().getHostRemainingTurns()-1);
        else GameController.getGame().setGuestRemainingTurns(GameController.getGame().getGuestRemainingTurns()-1);
    }
    public boolean isSpecial(){
        String str=this.character;
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
