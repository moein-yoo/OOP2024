package Model;

import Controller.GameController;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private  String[] hostRowStatus;//nothing-hole-card-broken
    private  String[] guestRowStatus;
    private  Card[] hostRowCards;//card names
    private  Card[] guestRowCards;


    private ArrayList<Card> hostCardsAtHand;
    private ArrayList<Card> guestCardsAtHand;
//    private User host;
//    private User guest;
    //boolean bet;
    private boolean hostTurn;
    private int hostRemainingTurns;
    private int guestRemainingTurns;
    private ArrayList<Integer> bonusCollectedindexesInHostRow;
    private ArrayList<Integer> bonusCollectedindexesInGuestRow;
    private int betAmount;
    private int hostInitialHP;
    private int guestInitialHP;

    public Game(){
        hostInitialHP=ApplicationData.getHost().getHP();
        guestInitialHP=ApplicationData.getGuest().getHP();
        int a = ApplicationData.getRandom().nextInt(21);
        int b = ApplicationData.getRandom().nextInt(21);
        bonusCollectedindexesInHostRow=new ArrayList<>();
        bonusCollectedindexesInGuestRow=new ArrayList<>();
        hostRowCards =new Card[21];
        guestRowCards =new Card[21];
        hostRowStatus =new String[21];
        guestRowStatus =new String[21];
        for(int i=0;i<21;i++){
            hostRowCards[i]=nullCard();
            guestRowCards[i]=nullCard();
            hostRowStatus[i]="nothing";
            guestRowStatus[i]="nothing";
        }
        hostRowStatus[a]="hole";
        guestRowStatus[b]="hole";
        hostCardsAtHand=new ArrayList<>();
        guestCardsAtHand=new ArrayList<>();
        for(int i=0;i<5;i++){
            hostCardsAtHand.add(randomCardReplace(true));
            guestCardsAtHand.add(randomCardReplace(false));
        }
        boolean startt= randomHostStart();
        if(startt)
            hostTurn =true;
        else
            hostTurn =false;
        for(int i=0;i<6;i++){
            int x=1;
            //fill deck for first time
        }
        hostRemainingTurns =4;
        guestRemainingTurns =4;
        betAmount=0;
    }
    public Game(int betAmount){
        int a = ApplicationData.getRandom().nextInt(21);
        int b = ApplicationData.getRandom().nextInt(21);
        bonusCollectedindexesInHostRow=new ArrayList<>();
        bonusCollectedindexesInGuestRow=new ArrayList<>();
        hostRowCards =new Card[21];
        guestRowCards =new Card[21];
        hostRowStatus =new String[21];
        guestRowStatus =new String[21];
        for(int i=0;i<21;i++){
            hostRowCards[i]=nullCard();
            guestRowCards[i]=nullCard();
            hostRowStatus[i]="nothing";
            guestRowStatus[i]="nothing";
        }
        hostRowStatus[a]="hole";
        guestRowStatus[b]="hole";
        hostCardsAtHand=new ArrayList<>();
        guestCardsAtHand=new ArrayList<>();
        for(int i=0;i<5;i++){
            hostCardsAtHand.add(randomCardReplace(true));
            guestCardsAtHand.add(randomCardReplace(false));
        }
        boolean startt= randomHostStart();
        if(startt)
            hostTurn =true;
        else
            hostTurn =false;
        for(int i=0;i<6;i++){
            int x=1;
            //fill deck for first time
        }
        hostRemainingTurns =4;
        guestRemainingTurns =4;
        this.betAmount=betAmount;
    }
    boolean randomHostStart(){//1:guest starts-2host starts
        int a= ApplicationData.getRandom().nextInt(3);
        if(a==0)
            return false;
        return true;
    }
    public Card randomCardReplace(boolean ishost){
        if(ishost){
            int r=0;
            for(Card card:hostCardsAtHand){if(card.isSpecial())r++;}
            Card card2= ApplicationData.getHost().getAllPossessedCards().get(ApplicationData.getRandom().nextInt(ApplicationData.getHost().getAllPossessedCards().size()));
            if(r>=2){
                while(card2.isSpecial())
                    card2=ApplicationData.getHost().getAllPossessedCards().get(ApplicationData.getRandom().nextInt(ApplicationData.getHost().getAllPossessedCards().size()));
            }
            while(cardInArrlist(card2,hostCardsAtHand))
                card2=ApplicationData.getHost().getAllPossessedCards().get(ApplicationData.getRandom().nextInt(ApplicationData.getHost().getAllPossessedCards().size()));
            return card2;
        }
        else{
            int r=0;
            for(Card card:guestCardsAtHand){if(card.isSpecial())r++;}
            Card card2= ApplicationData.getGuest().getAllPossessedCards().get(ApplicationData.getRandom().nextInt(ApplicationData.getGuest().getAllPossessedCards().size()));
            if(r>=2){
                while(card2.isSpecial())
                    card2=ApplicationData.getGuest().getAllPossessedCards().get(ApplicationData.getRandom().nextInt(ApplicationData.getGuest().getAllPossessedCards().size()));
            }
            while(cardInArrlist(card2,hostCardsAtHand))
                card2=ApplicationData.getGuest().getAllPossessedCards().get(ApplicationData.getRandom().nextInt(ApplicationData.getGuest().getAllPossessedCards().size()));
            return card2;
        }
    }
    private boolean cardInArrlist(Card card,ArrayList<Card> arrlist){
        for(Card x:arrlist){
            if(x.getName().equalsIgnoreCase(card.getName()))
                return true;
        }
        return false;
    }
    public Card nullCard(){
        Card card=new Card("1",0,0,0,"1",0,1);
        return card;
    }


    public String getHostRowStatus(int index) {
        return hostRowStatus[index];
    }
    public String[] getHostRowStatus() {
        return hostRowStatus;
    }

    public void setHostRowStatus(String str,int index) {
        this.hostRowStatus[index] = str;
    }

    public void setHostRowStatus(String[] list) {
        this.hostRowStatus = list;
    }

    public String getGuestRowStatus(int index) {
        return guestRowStatus[index];
    }
    public String[] getGuestRowStatus() {
        return guestRowStatus;
    }

    public void setGuestRowStatus(String str,int index) {
        this.guestRowStatus[index]=str;
    }

    public void setGuestRowStatus(String[] list) {
        this.guestRowStatus=list;
    }

    public Card[] getHostRowCards() {
        return hostRowCards;
    }

    public void setHostRowCards(Card card,int index) {
        this.hostRowCards[index] = card;
    }
    public void setHostRowCards(Card[] card) {
        this.hostRowCards = card;
    }

    public Card[] getGuestRowCards() {
        return guestRowCards;
    }

    public void setGuestRowCards(Card card,int index) {
        this.guestRowCards[index] = card;
    }
    public void setGuestRowCards(Card[] card) {
        this.guestRowCards = card;
    }

    public boolean isHostTurn() {
        return hostTurn;
    }

    public void setHostTurn() {
        this.hostTurn = !this.hostTurn;
    }

    public int getHostRemainingTurns() {
        return hostRemainingTurns;
    }

    public void setHostRemainingTurns(int hostRemainingTurns) {
        this.hostRemainingTurns = hostRemainingTurns;
    }

    public int getGuestRemainingTurns() {
        return guestRemainingTurns;
    }

    public void setGuestRemainingTurns(int guestRemainingTurns) {
        this.guestRemainingTurns = guestRemainingTurns;
    }

    public ArrayList<Card> getHostCardsAtHand() {
        return hostCardsAtHand;
    }

    public void setHostCardsAtHand(ArrayList<Card> hostCardsAtHand) {
        this.hostCardsAtHand = hostCardsAtHand;
    }

    public void addCardToHostCardsAtHand(Card card) {
        this.hostCardsAtHand.add(card);
    }
    public void removeCardFromHostCardsAtHand(int index) {
        this.hostCardsAtHand.remove(index);
    }
    public void removeCardFromHostCardsAtHand(Card card) {
        this.hostCardsAtHand.remove(card);
    }
     public ArrayList<Card> getGuestCardsAtHand() {
        return guestCardsAtHand;
    }

    public void setGuestCardsAtHand(ArrayList<Card> guestCardsAtHand) {
        this.guestCardsAtHand = guestCardsAtHand;
    }
    public void addCardToGuestCardsAtHand(Card card) {
        this.guestCardsAtHand.add(card);
    }
    public void removeCardFromGuestCardsAtHand(Card card) {
        this.guestCardsAtHand.remove(card);
    }
    public void removeCardFromGuestCardsAtHand(int index) {
        this.guestCardsAtHand.remove(index);
    }


    public ArrayList<Integer> getBonusCollectedindexesInGuestRow() {
        return bonusCollectedindexesInGuestRow;
    }
    public void addSthGuest(int a){this.getBonusCollectedindexesInGuestRow().add(a);}
    public void addSthHost(int a){this.getBonusCollectedindexesInHostRow().add(a);}
    public ArrayList<Integer> getBonusCollectedindexesInHostRow() {
        return bonusCollectedindexesInHostRow;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public int getHostInitialHP() {
        return hostInitialHP;
    }

    public int getGuestInitialHP() {
        return guestInitialHP;
    }
    public String hitSpecialCards(Card card,int index){
        int a=1;
        if(!isHostTurn())
            a=2;
//        if(card.getName().equalsIgnoreCase("separInstaller"))
//            separInstaller(index,a);
//        if(card.getName().equalsIgnoreCase("HPInstaller"))
//            HPInstaller(card.getDamage(),a);
        if(card.getName().equalsIgnoreCase("holeChanger"))
            return holeChanger(a,index);
        if(card.getName().equalsIgnoreCase("holeRemover"))
            return holeRemover(a,index);
        if(card.getName().equalsIgnoreCase("powerIncrease"))
            return powerIncrease(a);
        if(card.getName().equalsIgnoreCase("cardRemover"))
            return cardRemover(a);
        if(card.getName().equalsIgnoreCase("cardPowerDecrease"))
            return cardPowerDecrease(a);
        if(card.getName().equalsIgnoreCase("HPInstaller"))
            return HPInstaller(a);
        return "salam";
//        if(card.getName().equalsIgnoreCase("cardCopier"))
//            cardCopier(a);
//        if(card.getName().equ alsIgnoreCase("cardAtHandHider"))
//            cardsAtHandHider(a);
//        if(card.getName().equalsIgnoreCase("roundDec"))
//            roundDecreaseOfPlayer();
    }
//    public String separInstaller(int index,int player) {
//        if (index>20)
//            return "Out of Bounds";
//        else if (player == 1) {
//            guestRowStatus[index] = "broken";
//            setHostTurn();
////            guestRowCards[index] = null;
//        }
//        else {
//            hostRowStatus[index] = "broken";
//            setHostTurn();
////            hostRowCards[index] = null;
//        }
//        return "null";
//    }
    public String HPInstaller(int player) {//host=1
        if (player == 1) {
            ApplicationData.getHost().setHP(ApplicationData.getHost().getHP()+20);
        }
        else {
            ApplicationData.getGuest().setHP(ApplicationData.getGuest().getHP()+20);
        }
        return "HP increased!";
    }
    public String holeChanger(int player,int oldIndex) {
        if (player==1) {
            int index = ApplicationData.getRandom().nextInt(21);
            while (!hostRowStatus[index].equals("nothing"))
                index = ApplicationData.getRandom().nextInt(21);
            hostRowStatus[index] = "hole";hostRowStatus[oldIndex] = "nothing";
            return "hole changed!";
        }
        else {
            int index = ApplicationData.getRandom().nextInt(21);
            while (!guestRowStatus[index].equals("nothing"))
                index = ApplicationData.getRandom().nextInt(21);
            guestRowStatus[index] = "hole";guestRowStatus[oldIndex] = "nothing";
            return "hole changed!";
        }
    }

    public String holeRemover(int player, int index) {
        if (player==1) {
            hostRowStatus[index] = "nothing";
            return "hole removed";
        }
        else {
            guestRowStatus[index] = "nothing";
            return "hole removed";
        }
    }

//    public String roundDecreaseOfPlayer() {
//        this.setHostRemainingTurns(GameController.getGame().getHostRemainingTurns()-1);
//        this.setGuestRemainingTurns(GameController.getGame().getGuestRemainingTurns()-1);
//        return "rounds decreased";
//    }
    public String powerIncrease(int player) {
        if (player==1) {
            int index = ApplicationData.getRandom().nextInt(21);
            while (!hostRowStatus[index].equals("card")) {
                index = ApplicationData.getRandom().nextInt(21);
            }
            hostRowCards[index].setDamage(hostRowCards[index].getDamage()+hostRowCards[index].getDuration());
            hostRowCards[index].setAccuracy(hostRowCards[index].getAccuracy()+3);
            return "card power increased!";
        }
        else {
            int index = ApplicationData.getRandom().nextInt(21);
            while (!guestRowStatus[index].equals("card")) {
                index = ApplicationData.getRandom().nextInt(21);
            }
            guestRowCards[index].setDamage(guestRowCards[index].getDamage()+guestRowCards[index].getDuration());
            guestRowCards[index].setAccuracy(guestRowCards[index].getAccuracy()+3);
            return "card power increased";
        }
    }
    public String cardRemover(int player) {
        if (player==1) {
            int index = ApplicationData.getRandom().nextInt(GameController.getGame().getGuestCardsAtHand().size());
            Card card = this.getGuestCardsAtHand().get(index);
            this.removeCardFromGuestCardsAtHand(index);
            this.addCardToHostCardsAtHand(card);
            return "guest card removed!";
        }
        else {
            int index = ApplicationData.getRandom().nextInt(GameController.getGame().getHostCardsAtHand().size());
            Card card = GameController.getGame().getHostCardsAtHand().get(index);
            this.removeCardFromHostCardsAtHand(index);
            this.addCardToGuestCardsAtHand(card);
            return "host card removed!";
        }
    }
    public String cardPowerDecrease(int player) {
        int index1 = ApplicationData.getRandom().nextInt(21);
        int index2 = ApplicationData.getRandom().nextInt(21);
        if (player==1) {
            while (!guestRowStatus[index1].equals("card")) {
                index1 = ApplicationData.getRandom().nextInt(21);
            }
            while (!guestRowStatus[index2].equals("card") || index2==index1) {
                index2 = ApplicationData.getRandom().nextInt(21);
            }
            guestRowCards[index1].setAccuracy(guestRowCards[index1].getAccuracy()-5);
            guestRowCards[index2].setDamage(guestRowCards[index2].getDamage()- 2*guestRowCards[index2].getDuration());
            return "Guest card "+index1+"'s acc and card "+index2+"'s damage decreased";
        }
        else {
            index1 = ApplicationData.getRandom().nextInt(21);
            index2 = ApplicationData.getRandom().nextInt(21);
            while (!hostRowStatus[index1].equals("card")) {
                index1 = ApplicationData.getRandom().nextInt(21);
            }
            while (!hostRowStatus[index2].equals("card") || index2==index1) {
                index2 = ApplicationData.getRandom().nextInt(21);
            }
            hostRowCards[index1].setAccuracy(hostRowCards[index1].getAccuracy()-5);
            hostRowCards[index2].setDamage(hostRowCards[index1].getDamage()- 2*hostRowCards[index1].getDuration());
            return "Host card "+index1+"'s acc and card "+index2+"'s damage decreased";
        }
    }



}
